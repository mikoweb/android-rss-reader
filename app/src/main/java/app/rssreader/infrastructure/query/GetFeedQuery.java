package app.rssreader.infrastructure.query;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import app.rssreader.application.logic.datafactory.FeedRssDataFactory;
import app.rssreader.domain.dto.rss.Rss;
import app.rssreader.domain.value.feed.FeedObject;
import app.rssreader.infrastructure.reader.RssFeedReader;

public class GetFeedQuery {
    private final FeedRssDataFactory feedRssDataFactory;

    @Inject
    public GetFeedQuery(FeedRssDataFactory feedRssDataFactory) {
        this.feedRssDataFactory = feedRssDataFactory;
    }

    public FeedObject getFeed(String url) throws ExecutionException, InterruptedException {
        RssFeedReader reader = new RssFeedReader();
        Rss rss = reader.read(url);

        return feedRssDataFactory.createFromRss(rss);
    }
}
