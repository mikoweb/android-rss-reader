package app.rssreader.application.logic.datafactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import app.rssreader.application.logic.html.HtmlUtil;
import app.rssreader.domain.dto.rss.Channel;
import app.rssreader.domain.dto.rss.Item;
import app.rssreader.domain.dto.rss.Rss;
import app.rssreader.domain.value.feed.FeedDetailsObject;
import app.rssreader.domain.value.feed.FeedItemObject;
import app.rssreader.domain.value.feed.FeedObject;

public class FeedRssDataFactory {
    @Inject
    public FeedRssDataFactory() {}

    public FeedObject createFromRss(Rss rss) {
        List<FeedItemObject> items = new ArrayList<>();

        for (Item item: rss.getChannel().getItems()) {
            items.add(this.createItem(item));
        }

        return new FeedObject(this.createDetails(rss), items);
    }

    private FeedDetailsObject createDetails(Rss rss) {
        Channel channel = rss.getChannel();
        String image = null;

        if (channel.getImage() != null) {
            image = channel.getImage().getUrl();
        }

        return new FeedDetailsObject(
            Optional.ofNullable(channel.getTitle()).orElse("").trim(),
            HtmlUtil.removeTags(Optional.ofNullable(channel.getDescription()).orElse("")),
            Optional.ofNullable(channel.getLink()).orElse(""),
            image
        );
    }

    private FeedItemObject createItem(Item item) {
        return new FeedItemObject(
            Optional.ofNullable(item.getTitle()).orElse("").trim(),
            HtmlUtil.removeTags(Optional.ofNullable(item.getDescription()).orElse("")),
            Optional.ofNullable(item.getLink()).orElse(""),
            item.getImageUrl()
        );
    }
}
