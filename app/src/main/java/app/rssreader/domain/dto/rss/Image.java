package app.rssreader.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement
public class Image {
    private String url;
    private String title;
    private String link;
}
