package app.rssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import app.rssreader.ui.theme.AppTheme
import app.rssreader.ui.theme.MessageViewModel
import app.rssreader.ui.theme.element.RssTextBoxViewModel
import app.rssreader.ui.theme.layout.DrawerViewModel
import app.rssreader.ui.theme.section.RssItemsSectionViewModel
import app.rssreader.ui.theme.section.bookmarks.BookmarkCreateViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppLayout()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppLayout() {
    AppTheme()
}
