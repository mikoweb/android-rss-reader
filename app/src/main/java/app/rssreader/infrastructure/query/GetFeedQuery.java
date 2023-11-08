package app.rssreader.infrastructure.query;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import app.rssreader.application.logic.datafactory.FeedRssDataFactory;
import app.rssreader.domain.dto.rss.Rss;
import app.rssreader.domain.value.feed.FeedObject;
import app.rssreader.infrastructure.reader.RssFeedReader;

public class GetFeedQuery {
    private final FeedRssDataFactory feedRssDataFactory;
    private final RssFeedReader reader;

    @Inject
    public GetFeedQuery(FeedRssDataFactory feedRssDataFactory, RssFeedReader reader) {
        this.feedRssDataFactory = feedRssDataFactory;
        this.reader = reader;
    }

    public FeedObject getFeed(String url) throws ExecutionException, InterruptedException {
        Rss rss = reader.read(url);

        return feedRssDataFactory.createFromRss(rss);
    }
}
