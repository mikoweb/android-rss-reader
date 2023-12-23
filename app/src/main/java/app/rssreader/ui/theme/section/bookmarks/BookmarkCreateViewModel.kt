package app.rssreader.ui.theme.section.bookmarks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.rssreader.application.command.AddBookmarkCommand
import app.rssreader.application.logic.viewmodel.AppViewModel
import app.rssreader.domain.dto.bookmark.BookmarkItemDto
import org.apache.commons.validator.routines.UrlValidator
import java.util.UUID
import javax.inject.Inject

class BookmarkCreateViewModel @Inject constructor() : AppViewModel() {
    @Inject
    lateinit var addBookmarkCommand: AddBookmarkCommand

    var isValid: Boolean by mutableStateOf(false)
        private set

    var url by mutableStateOf("")
        private set

    var urlIsValid: Boolean by mutableStateOf(false)
        private set

    var name by mutableStateOf("")
        private set

    var nameIsValid: Boolean by mutableStateOf(false)
        private set

    fun updateUrl(input: String) {
        url = input
        urlIsValid = getUrlIsValidValue()
        updateIsValid()
    }

    fun updateName(input: String) {
        name = input
        nameIsValid = getNameIsValidValue()
        updateIsValid()
    }

    fun submit() {
        addBookmarkCommand.run(BookmarkItemDto(UUID.randomUUID().toString(), name, url))
        reset()
    }

    fun reset() {
        updateUrl("")
        updateName("")
    }

    private fun getUrlIsValidValue(): Boolean {
        val validator = UrlValidator()
        return url.isNotEmpty() && validator.isValid(url)
    }

    private fun getNameIsValidValue(): Boolean {
        return name.isNotEmpty()
    }

    private fun updateIsValid() {
        isValid = getUrlIsValidValue() && getNameIsValidValue()
    }
}
