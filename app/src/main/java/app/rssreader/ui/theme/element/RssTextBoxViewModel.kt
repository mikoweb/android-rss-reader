package app.rssreader.ui.theme.element

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.rssreader.application.command.LoadRssFeedCommand
import app.rssreader.application.logic.viewmodel.AppViewModel
import javax.inject.Inject

class RssTextBoxViewModel @Inject constructor() : AppViewModel() {
    @Inject
    lateinit var loadRssFeedCommand: LoadRssFeedCommand

    var url by mutableStateOf("")
        private set

    fun updateUrl(context: Context, input: String) {
        url = input
        loadRssFeedCommand.run(context, url)
    }
}
