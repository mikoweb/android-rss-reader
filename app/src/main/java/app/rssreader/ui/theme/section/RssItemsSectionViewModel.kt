package app.rssreader.ui.theme.section

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import app.rssreader.application.logic.viewmodel.AppViewModel
import app.rssreader.domain.value.feed.FeedObject
import javax.inject.Inject

class RssItemsSectionViewModel @Inject constructor() : AppViewModel() {
    var feedObject: FeedObject? by mutableStateOf(null)
        private set

    fun updateFeedObject(input: FeedObject?) {
        feedObject = input
    }
}
