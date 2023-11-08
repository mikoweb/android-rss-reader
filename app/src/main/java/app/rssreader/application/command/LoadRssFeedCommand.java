package app.rssreader.application.command;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.commons.validator.routines.UrlValidator;

import javax.inject.Inject;

import app.rssreader.application.logic.toast.ToastService;
import app.rssreader.domain.value.feed.FeedObject;
import app.rssreader.infrastructure.query.GetFeedQuery;

public class LoadRssFeedCommand {
    private final GetFeedQuery getFeedQuery;

    @Inject
    public LoadRssFeedCommand(GetFeedQuery getFeedQuery) {
        this.getFeedQuery = getFeedQuery;
    }

    public boolean run(Context context, String url) throws JsonProcessingException {
        UrlValidator validator = new UrlValidator();

        if (!validator.isValid(url)) {
            showInvalidUrlMessage(context);
            return false;
        }

        FeedObject feed = null;

        try {
            // TODO remove
            // feed = getFeedQuery.getFeed("https://wydarzenia.interia.pl/polska/feed");
            // feed = getFeedQuery.getFeed("https://wiadomosci.wp.pl/rss.xml");
            feed = getFeedQuery.getFeed(url);
        } catch (IllegalArgumentException exception) {
            this.showInvalidUrlMessage(context);
        } catch (Throwable throwable) {
            this.showFailRssMessage(context);
        }

        if (feed != null) {
            // TODO load items

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(feed);

            Log.i("Sample", "RSS Content:");
            Log.i("Sample", json);
        }

        return feed != null;
    }

    private void showInvalidUrlMessage(Context context) {
        ToastService.showMessage(context, "Nieprawidłowy adres!");
    }

    private void showFailRssMessage(Context context) {
        ToastService.showMessage(context, "Nie udało się pobrać RSS!");
    }
}
