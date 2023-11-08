package app.rssreader.application.command;

import android.content.Context;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import app.rssreader.application.logic.toast.ToastService;
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

    public void run(Context context, String url) {
        update(null);
        UrlValidator validator = new UrlValidator();

        if (!validator.isValid(url)) {
            showInvalidUrlMessage(context);
            return;
        }

        rssItemsSectionViewModel.updateLoading(true);
        CompletableFuture.runAsync(() -> {
            try {
                FeedObject feed = getFeedQuery.getFeed(url);
                update(feed);
                rssItemsSectionViewModel.updateLoading(false);
            } catch (IllegalArgumentException exception) {
                this.showInvalidUrlMessage(context);
            } catch (Throwable throwable) {
                this.showFailRssMessage(context);
            } finally {
                rssItemsSectionViewModel.updateLoading(false);
            }
        });
    }

    private void update(FeedObject feed) {
        this.rssItemsSectionViewModel.updateFeedObject(feed);
    }

    private void showInvalidUrlMessage(Context context) {
        ToastService.showMessage(context, "Nieprawidłowy adres!");
    }

    private void showFailRssMessage(Context context) {
        ToastService.showMessage(context, "Nie udało się pobrać RSS!");
    }
}
