package app.rssreader.application.command;

import java.util.ArrayList;

import javax.inject.Inject;

import app.rssreader.domain.dto.bookmark.BookmarkItemDto;
import app.rssreader.infrastructure.query.GetBookmarksQuery;
import app.rssreader.ui.theme.section.bookmarks.BookmarkListViewModel;

public class LoadBookmarksCommand {
    private final GetBookmarksQuery getBookmarksQuery;
    private final BookmarkListViewModel bookmarkListViewModel;

    @Inject
    public LoadBookmarksCommand(
        GetBookmarksQuery getBookmarksQuery,
        BookmarkListViewModel bookmarkListViewModel
    ) {
        this.getBookmarksQuery = getBookmarksQuery;
        this.bookmarkListViewModel = bookmarkListViewModel;
    }

    public void run() {
        ArrayList<BookmarkItemDto> list = getBookmarksQuery.getBookmarksSafe();
        bookmarkListViewModel.updateList(list);
    }
}
