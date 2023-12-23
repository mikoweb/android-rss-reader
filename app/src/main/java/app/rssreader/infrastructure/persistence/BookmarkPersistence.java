package app.rssreader.infrastructure.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;

import app.rssreader.domain.dto.bookmark.BookmarkItemDto;
import app.rssreader.infrastructure.bookmark.path.BookmarksPath;
import app.rssreader.infrastructure.query.GetBookmarksQuery;

public class BookmarkPersistence {
    private final BookmarksPath bookmarksPath;
    private final GetBookmarksQuery getBookmarksQuery;
    private final ObjectMapper objectMapper;

    @Inject
    public BookmarkPersistence(
        BookmarksPath bookmarksPath,
        GetBookmarksQuery getBookmarksQuery
    ) {
        this.bookmarksPath = bookmarksPath;
        this.getBookmarksQuery = getBookmarksQuery;
        this.objectMapper = new ObjectMapper();
    }

    public void add(BookmarkItemDto item) throws IOException {
        ArrayList<BookmarkItemDto> list = getBookmarksQuery.getBookmarks();
        list.add(item);
        save(list);
    }

    public void delete(String id) throws IOException {
        ArrayList<BookmarkItemDto> list = getBookmarksQuery.getBookmarks();
        Optional<BookmarkItemDto> bookmark = getBookmarksQuery.findOneById(list, id);

        if (bookmark.isPresent()) {
            list.remove(bookmark.get());
            save(list);
        }
    }

    public void save(ArrayList<BookmarkItemDto> list) throws IOException {
        objectMapper.writeValue(bookmarksPath.getPath(), list);
    }
}
