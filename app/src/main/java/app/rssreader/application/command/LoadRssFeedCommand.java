package app.rssreader.application.command;

import org.apache.commons.validator.routines.UrlValidator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import app.rssreader.domain.value.feed.FeedObject;
import app.rssreader.infrastructure.query.GetFeedQuery;
import app.rssreader.ui.theme.section.RssItemsSectionViewModel;

public class LoadRssFeedCommand {
    private final GetFeedQuery getFeedQuery;
    private final RssItemsSectionViewModel rssItemsSectionViewModel;

    @Inject
    public LoadRssFeedCommand(
        GetFeedQuery getFeedQuery,
        RssItemsSectionViewModel rssItemsSectionViewModel
    ) {
        this.getFeedQuery = getFeedQuery;
        this.rssItemsSectionViewModel = rssItemsSectionViewModel;
    }

    public void run(String url) {
        update(null);
        UrlValidator validator = new UrlValidator();

        if (!validator.isValid(url)) {
            throw new IllegalArgumentException("Invalid URL!");
        }

        rssItemsSectionViewModel.updateLoading(true);
        CompletableFuture.runAsync(() -> {
            try {
                FeedObject feed = getFeedQuery.getFeed(url);
                update(feed);
                rssItemsSectionViewModel.updateLoading(false);
            } catch (IllegalArgumentException exception) {
                throw exception;
            } catch (Throwable throwable) {
                try {
                    throw throwable;
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                rssItemsSectionViewModel.updateLoading(false);
            }
        });
    }

    private void update(FeedObject feed) {
        this.rssItemsSectionViewModel.updateFeedObject(feed);
    }
}
