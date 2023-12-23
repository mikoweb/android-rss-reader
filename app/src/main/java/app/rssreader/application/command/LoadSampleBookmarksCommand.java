package app.rssreader.application.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import app.rssreader.domain.dto.bookmark.BookmarkItemDto;
import app.rssreader.infrastructure.persistence.BookmarkPersistence;
import app.rssreader.infrastructure.query.GetBookmarksQuery;

public class LoadSampleBookmarksCommand {
    private final GetBookmarksQuery getBookmarksQuery;
    private final BookmarkPersistence bookmarkPersistence;

    @Inject
    public LoadSampleBookmarksCommand(
        GetBookmarksQuery getBookmarksQuery,
        BookmarkPersistence bookmarkPersistence
    ) {
        this.getBookmarksQuery = getBookmarksQuery;
        this.bookmarkPersistence = bookmarkPersistence;
    }

    public void run() throws IOException {
        ArrayList<BookmarkItemDto> list = getBookmarksQuery.getBookmarksSafe();

        createBookmark(list, new BookmarkItemDto(
            UUID.randomUUID().toString(),
            "Interia - Wydarzenia",
            "https://wydarzenia.interia.pl/feed"
        ));

        createBookmark(list, new BookmarkItemDto(
            UUID.randomUUID().toString(),
            "Polsat News",
            "https://www.polsatnews.pl/rss/wszystkie.xml"
        ));

        createBookmark(list, new BookmarkItemDto(
            UUID.randomUUID().toString(),
            "Antyweb - blog technologiczny",
            "https://antyweb.pl/feed/"
        ));

        createBookmark(list, new BookmarkItemDto(
            UUID.randomUUID().toString(),
            "Money.pl",
            "https://www.money.pl/rss/rss.xml"
        ));

        createBookmark(list, new BookmarkItemDto(
            UUID.randomUUID().toString(),
            "GeekWeek",
            "https://geekweek.interia.pl/feed"
        ));

        createBookmark(list, new BookmarkItemDto(
            UUID.randomUUID().toString(),
            "Motorsport.com: F1 News",
            "https://pl.motorsport.com/rss/f1/news/"
        ));
    }

    private void createBookmark(
        ArrayList<BookmarkItemDto> list,
        BookmarkItemDto item
    ) throws IOException {
        Optional<BookmarkItemDto> found = getBookmarksQuery.findOneByUrl(list, item.url);

        if (!found.isPresent()) {
            bookmarkPersistence.add(item);
        }
    }
}
