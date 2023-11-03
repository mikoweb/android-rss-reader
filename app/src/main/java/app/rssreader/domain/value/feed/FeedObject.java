package app.rssreader.domain.value.feed;

import java.util.List;

import lombok.Data;

@Data
public class FeedObject {
    private FeedDetailsObject details;
    private List<FeedItemObject> items;

    public FeedObject(FeedDetailsObject details, List<FeedItemObject> items) {
        this.details = details;
        this.items = items;
    }
}
