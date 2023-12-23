package app.rssreader.ui.theme.section.bookmarks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.rssreader.application.logic.viewmodel.ViewModelMap
import app.rssreader.ui.theme.MessageViewModel
import app.rssreader.ui.theme.element.AppMainContainer
import app.rssreader.ui.theme.element.AppMainHeading
import app.rssreader.ui.theme.element.RssTextBoxViewModel
import app.rssreader.ui.theme.layout.DrawerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppBookmarkListSection() {
    val viewModel = ViewModelMap.get(BookmarkListViewModel::class.java) as BookmarkListViewModel
    val drawerViewModel = ViewModelMap.get(DrawerViewModel::class.java) as DrawerViewModel
    val rssTextBoxViewModel = ViewModelMap.get(RssTextBoxViewModel::class.java) as RssTextBoxViewModel
    val messageViewModel = ViewModelMap.get(MessageViewModel::class.java) as MessageViewModel
    val bookmarks = viewModel.list

    AppMainContainer {
        AppMainHeading("Lista zakładek")

        if (bookmarks != null) {
            Column {
                bookmarks.forEach { bookmark ->
                    ListItem(
                        headlineText = {
                            Row(
                                modifier = Modifier.clickable {
                                    rssTextBoxViewModel.updateUrl(bookmark.url)
                                    drawerViewModel.changeSelected("home")
                                }
                            ) {
                                Icon(Icons.Outlined.ArrowForward, contentDescription = "Link")
                                Text(
                                    text = bookmark.name,
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                                )
                            }
                        },
                        trailingContent = {
                            IconButton(onClick = {
                                try {
                                    viewModel.deleteAction(bookmark.id)
                                    messageViewModel.createSuccess("Zakładka została usunięta")
                                } catch (throwable: Throwable) {
                                    messageViewModel.createError("Nie udało się usunąć zakładki!")
                                }
                            }) {
                                Icon(Icons.Outlined.Delete, contentDescription = "Usuń")
                            }
                        },
                    )
                    Divider()
                }
            }
        }
    }
}
