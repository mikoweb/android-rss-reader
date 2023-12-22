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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appDrawerState(): DrawerState {
    return rememberDrawerState(initialValue = DrawerValue.Closed);
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(drawerState: DrawerState, content: @Composable () -> Unit) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                Divider()
                Text("")
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "") },
                    label = { Text(text = "Okno główne") },
                    selected = true,
                    onClick = {}
                )
                Text("Zakładki", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "") },
                    label = { Text(text = "Dodaj zakładę") },
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "") },
                    label = { Text(text = "Lista zakładek") },
                    selected = false,
                    onClick = {}
                )
            }
        }
    ) {
        content()
    }
}
