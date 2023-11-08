package app.rssreader.domain.value.feed;

import lombok.Data;

@Data
public class FeedItemObject {
    public String title;
    public String description;
    public String link;
    public String image;

    public FeedItemObject(String title, String description, String link, String image) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.image = image;
    }
}
