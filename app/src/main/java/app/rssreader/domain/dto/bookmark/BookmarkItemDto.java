package app.rssreader.domain.dto.bookmark;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JacksonXmlRootElement
@NoArgsConstructor
public class BookmarkItemDto {
    public String id;
    public String name;
    public String url;

    public BookmarkItemDto(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
