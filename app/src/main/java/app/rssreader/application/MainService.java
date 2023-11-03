package app.rssreader.application;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import app.rssreader.domain.value.feed.FeedObject;
import app.rssreader.infrastructure.query.GetFeedQuery;

public class MainService {
    private final GetFeedQuery getFeedQuery;

    @Inject
    public MainService(GetFeedQuery getFeedQuery) {
        this.getFeedQuery = getFeedQuery;
    }

    public void main() throws ExecutionException, InterruptedException, JsonProcessingException {
        Log.d("Sample","Application started!");

        testRss();
    }

    private void testRss() throws ExecutionException, InterruptedException, JsonProcessingException {
        FeedObject feed = getFeedQuery.getFeed("https://wydarzenia.interia.pl/polska/feed");
        //FeedObject feed = getFeedQuery.getFeed("https://wiadomosci.wp.pl/rss.xml");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(feed);

        Log.i("Sample", "RSS Content:");
        Log.i("Sample", json);
    }
}
