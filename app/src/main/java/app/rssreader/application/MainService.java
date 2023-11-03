package app.rssreader.application;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import app.rssreader.infrastructure.reader.RssFeedReader;
import app.rssreader.domain.dto.rss.Rss;

public class MainService {
    @Inject
    public MainService() {}

    public void main() throws ExecutionException, InterruptedException, JsonProcessingException {
        Log.d("Sample","Application started!");

        testRss();
    }

    private void testRss() throws ExecutionException, InterruptedException, JsonProcessingException {
        RssFeedReader reader = new RssFeedReader();
        Rss rss = reader.read("https://wydarzenia.interia.pl/polska/feed");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(rss);

        Log.i("Sample", "RSS Content:");
        Log.i("Sample", json);
    }
}
