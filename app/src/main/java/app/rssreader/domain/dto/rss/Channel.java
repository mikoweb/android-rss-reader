package app.rssreader.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

import lombok.Data;

@Data
@JacksonXmlRootElement
public class Channel {
    private String title;
    private String description;
    private String link;
    private Image image;
    private String lastBuildDate;
    private String pubDate;
    private String language;
    private String category;

    @JacksonXmlProperty(localName = "item")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Item> items;
}
