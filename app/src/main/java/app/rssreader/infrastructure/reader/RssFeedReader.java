package app.rssreader.infrastructure.reader;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import app.rssreader.infrastructure.reader.exception.NoRssContentException;
import app.rssreader.infrastructure.reader.exception.RssMappingException;
import app.rssreader.domain.dto.rss.Rss;
import app.rssreader.domain.reader.RssReader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RssFeedReader implements RssReader {
    private final OkHttpClient client;
    private final XmlMapper objectMapper;

    private abstract static class RssResponseCallback extends CompletableFuture<String> implements Callback {
    }

    @Inject
    public RssFeedReader() {
        this.client = new OkHttpClient();
        this.objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Rss read(String url) throws RssMappingException, NoRssContentException, ExecutionException, InterruptedException {
        CompletableFuture<String> contentFuture = fetchFeed(url);
        String content = contentFuture.get();

        return mapRss(content);
    }

    private Rss mapRss(String content) {
        try {
            return objectMapper.readValue(content, Rss.class);
        } catch (Throwable throwable) {
            throw new RssMappingException(throwable);
        }
    }

    private CompletableFuture<String> fetchFeed(String url) {
        Request request = new Request.Builder()
            .url(url)
            .build();

        CompletableFuture<String> future = new RssResponseCallback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    super.complete(response.body().string());
                } else {
                    super.completeExceptionally(new NoRssContentException());
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                super.completeExceptionally(e);
            }
        };

        client.newCall(request).enqueue((Callback) future);

        return future;
    }
}
