package app.rssreader.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement
public class Enclosure {
    private String url;
    private String type;
    private int length;
}
