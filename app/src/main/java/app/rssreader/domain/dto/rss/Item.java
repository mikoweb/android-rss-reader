package app.rssreader.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

import lombok.Data;

@Data
@JacksonXmlRootElement
public class Item {
    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String guid;
    private String category;

    @JacksonXmlProperty(localName = "enclosure")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Enclosure> enclosures;
}
