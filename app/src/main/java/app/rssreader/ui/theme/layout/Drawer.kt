package app.rssreader.ui.theme.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.rssreader.application.logic.viewmodel.ViewModelMap
import app.rssreader.ui.theme.section.bookmarks.BookmarkCreateViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.timerTask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appDrawerState(): DrawerState {
    return rememberDrawerState(initialValue = DrawerValue.Closed);
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(content: @Composable () -> Unit) {
    val drawerViewModel = ViewModelMap.get(DrawerViewModel::class.java) as DrawerViewModel
    val bookmarkCreateViewModel = ViewModelMap.get(BookmarkCreateViewModel::class.java) as BookmarkCreateViewModel
    val scope = rememberCoroutineScope()

    fun itemCommonClick(selected: String) {
        drawerViewModel.changeSelected(selected)
        scope.launch {
            drawerViewModel.drawerState?.apply {
                drawerViewModel.drawerState?.close()
            }
        }
    }

    drawerViewModel.drawerState?.let {
        ModalNavigationDrawer(
            drawerState = it,
            drawerContent = {
                ModalDrawerSheet {
                    Text("Menu", modifier = Modifier.padding(16.dp))
                    Divider()
                    Text("")
                    NavigationDrawerItem(
                        icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "") },
                        label = { Text(text = "Okno główne") },
                        selected = drawerViewModel.selected == "home",
                        onClick = {
                            itemCommonClick("home")
                        }
                    )
                    Text("Zakładki", modifier = Modifier.padding(16.dp))
                    NavigationDrawerItem(
                        icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "") },
                        label = { Text(text = "Dodaj zakładę") },
                        selected = drawerViewModel.selected == "bookmarks_add",
                        onClick = {
                            itemCommonClick("bookmarks_add")
                            bookmarkCreateViewModel.reset()
                            Timer().schedule(timerTask {
                                bookmarkCreateViewModel.reset()
                            }, 100)
                        }
                    )
                    NavigationDrawerItem(
                        icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "") },
                        label = { Text(text = "Lista zakładek") },
                        selected = drawerViewModel.selected == "bookmarks_list",
                        onClick = {
                            drawerViewModel.listAction()
                            itemCommonClick("bookmarks_list")
                        }
                    )
                }
            }
        ) {
            content()
        }
    }
}
