package app.rssreader.ui.theme.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.rssreader.application.logic.viewmodel.ViewModelMap
import app.rssreader.ui.theme.layout.DrawerViewModel
import app.rssreader.ui.theme.section.AppMainSection
import app.rssreader.ui.theme.section.bookmarks.AppBookmarkCreateSection
import app.rssreader.ui.theme.section.bookmarks.AppBookmarkListSection

@Composable
@Preview
fun AppRouter() {
    val drawerViewModel = ViewModelMap.get(DrawerViewModel::class.java) as DrawerViewModel

    when (drawerViewModel.selected) {
        "home" -> AppMainSection()
        "bookmarks_add" -> AppBookmarkCreateSection()
        "bookmarks_list" -> AppBookmarkListSection()
    }
}
