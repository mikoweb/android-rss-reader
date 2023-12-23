package app.rssreader.application.command;

import java.io.IOException;

import javax.inject.Inject;

import app.rssreader.application.logic.viewmodel.ViewModelMap;
import app.rssreader.application.logic.viewmodel.exception.NotFoundViewModelException;
import app.rssreader.infrastructure.persistence.BookmarkPersistence;
import app.rssreader.infrastructure.query.GetBookmarksQuery;
import app.rssreader.ui.theme.section.bookmarks.BookmarkListViewModel;

public class DeleteBookmarkCommand {
    private final BookmarkPersistence bookmarkPersistence;
    private final GetBookmarksQuery getBookmarksQuery;

    @Inject
    public DeleteBookmarkCommand(
        BookmarkPersistence bookmarkPersistence,
        GetBookmarksQuery getBookmarksQuery
    ) {
        this.bookmarkPersistence = bookmarkPersistence;
        this.getBookmarksQuery = getBookmarksQuery;
    }

    public void run(String id) throws NotFoundViewModelException, IOException {
        bookmarkPersistence.delete(id);

        BookmarkListViewModel bookmarkListViewModel
                = (BookmarkListViewModel) ViewModelMap.get(BookmarkListViewModel.class);

        bookmarkListViewModel.updateList(getBookmarksQuery.getBookmarksSafe());
    }
}
