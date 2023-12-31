package app.rssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import app.rssreader.application.config.AppConfig
import app.rssreader.infrastructure.persistence.BookmarkPersistence
import app.rssreader.ui.theme.AppTheme
import app.rssreader.ui.theme.MessageViewModel
import app.rssreader.ui.theme.element.RssTextBoxViewModel
import app.rssreader.ui.theme.layout.DrawerViewModel
import app.rssreader.ui.theme.section.RssItemsSectionViewModel
import app.rssreader.ui.theme.section.bookmarks.BookmarkCreateViewModel
import app.rssreader.ui.theme.section.bookmarks.BookmarkListViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var bookmarkPersistence: BookmarkPersistence

    @Inject
    lateinit var messageViewModel: MessageViewModel

    @Inject
    lateinit var rssTextBoxViewModel: RssTextBoxViewModel

    @Inject
    lateinit var rssItemsSectionViewModel: RssItemsSectionViewModel

    @Inject
    lateinit var drawerViewModel: DrawerViewModel

    @Inject
    lateinit var bookmarkCreateViewModel: BookmarkCreateViewModel

    @Inject
    lateinit var bookmarkListViewModel: BookmarkListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).appComponent.inject(this)
        setConfig()
        bookmarkPersistence.initStorage()

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppLayout()
        }
    }

    private fun setConfig() {
        AppConfig.setBookmarksFilePath(
            applicationContext.getFileStreamPath("rss_reader_bookmarks.json")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppLayout() {
    AppTheme()
}
