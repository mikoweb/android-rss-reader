package app.rssreader.application.command;

import java.io.IOException;

import javax.inject.Inject;

import app.rssreader.domain.dto.bookmark.BookmarkItemDto;
import app.rssreader.infrastructure.persistence.BookmarkPersistence;

public class AddBookmarkCommand {
    private final BookmarkPersistence bookmarkPersistence;

    @Inject
    public AddBookmarkCommand(BookmarkPersistence bookmarkPersistence) {
        this.bookmarkPersistence = bookmarkPersistence;
    }

    public void run(BookmarkItemDto item) throws IOException {
        bookmarkPersistence.add(item);
    }
}
