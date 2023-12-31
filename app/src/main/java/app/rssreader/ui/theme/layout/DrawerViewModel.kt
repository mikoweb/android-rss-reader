package app.rssreader.ui.theme.layout

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.rssreader.application.command.LoadBookmarksCommand
import app.rssreader.application.command.LoadSampleBookmarksCommand
import app.rssreader.application.logic.viewmodel.AppViewModel
import javax.inject.Inject

class DrawerViewModel @Inject constructor() : AppViewModel() {
    @Inject
    lateinit var loadBookmarksCommand: LoadBookmarksCommand

    @Inject
    lateinit var loadSampleBookmarksCommand: LoadSampleBookmarksCommand

    var selected: String by mutableStateOf("home")
        private set

    @OptIn(ExperimentalMaterial3Api::class)
    var drawerState: DrawerState? by mutableStateOf(null)

    fun changeSelected(input: String) {
        selected = input
    }

    fun listAction() {
        loadBookmarksCommand.run()
    }

    fun loadSampleBookmarks() {
        loadSampleBookmarksCommand.run()
    }
}
