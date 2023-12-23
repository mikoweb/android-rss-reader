package app.rssreader.infrastructure.query;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;

import app.rssreader.domain.dto.bookmark.BookmarkItemDto;
import app.rssreader.infrastructure.bookmark.path.BookmarksPath;

public class GetBookmarksQuery {
    private final BookmarksPath bookmarksPath;
    private final ObjectMapper objectMapper;

    @Inject
    public GetBookmarksQuery(BookmarksPath bookmarksPath) {
        this.bookmarksPath = bookmarksPath;
        this.objectMapper = new ObjectMapper();
    }

    public ArrayList<BookmarkItemDto> getBookmarks() throws IOException {
        return objectMapper.readerForListOf(BookmarkItemDto.class).readValue(bookmarksPath.getPath());
    }

    public ArrayList<BookmarkItemDto> getBookmarksSafe() {
        try {
            return getBookmarks();
        } catch (IOException exception) {
            return new ArrayList<>();
        }
    }

    public Optional<BookmarkItemDto> findOneById(ArrayList<BookmarkItemDto> list, String id) {
        return list.stream()
            .filter(bookmark -> bookmark.getId().equals(id))
            .findAny();
    }
}
