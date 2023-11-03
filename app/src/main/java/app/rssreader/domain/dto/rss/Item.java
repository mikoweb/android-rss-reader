package app.rssreader.domain.dto.rss;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import app.rssreader.application.logic.html.HtmlUtil;
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

    public String getImageUrl() {
        String url = null;
        List<Enclosure> images = getImagesEnclosures();

        if (!images.isEmpty()) {
            url = images.get(0).getUrl();
        }

        if (url == null) {
            List<String> urls = HtmlUtil.findImagesSrc(description);

            if (!urls.isEmpty()) {
                url = urls.get(0);
            }
        }

        return url;
    }

    public List<Enclosure> getImagesEnclosures() {
        return Optional.ofNullable(enclosures).orElse(new ArrayList<>()).stream().filter(
            enclosure -> enclosure.getType().startsWith("image/")
        ).collect(Collectors.toList());
    }
}
