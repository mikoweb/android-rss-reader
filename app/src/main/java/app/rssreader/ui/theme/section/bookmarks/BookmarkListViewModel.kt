package app.rssreader.ui.theme.section.bookmarks

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.rssreader.application.command.DeleteBookmarkCommand
import app.rssreader.application.logic.viewmodel.AppViewModel
import app.rssreader.domain.dto.bookmark.BookmarkItemDto
import java.util.ArrayList
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
class BookmarkListViewModel @Inject constructor() : AppViewModel() {
    @Inject
    lateinit var deleteBookmarkCommand: DeleteBookmarkCommand

    var list: ArrayList<BookmarkItemDto>? by mutableStateOf(null)
        private set

    fun updateList(input: ArrayList<BookmarkItemDto>?) {
        list = input
    }

    fun deleteAction(id: String) {
        deleteBookmarkCommand.run(id);
    }
}
