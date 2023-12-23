package app.rssreader.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.rssreader.application.logic.viewmodel.ViewModelMap
import app.rssreader.ui.theme.layout.AppDrawer
import app.rssreader.ui.theme.layout.AppFooter
import app.rssreader.ui.theme.layout.AppHeader
import app.rssreader.ui.theme.layout.DrawerViewModel
import app.rssreader.ui.theme.layout.appDrawerState
import app.rssreader.ui.theme.router.AppRouter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AppTheme() {
    val drawerViewModel = ViewModelMap.get(DrawerViewModel::class.java) as DrawerViewModel
    drawerViewModel.drawerState = appDrawerState()

    val messageViewModel = ViewModelMap.get(MessageViewModel::class.java) as MessageViewModel
    messageViewModel.scope = rememberCoroutineScope()
    messageViewModel.successMessage = remember { SnackbarHostState() }
    messageViewModel.errorMessage = remember { SnackbarHostState() }
    messageViewModel.warningMessage = remember { SnackbarHostState() }
    messageViewModel.infoMessage = remember { SnackbarHostState() }

    val snackbarModifier = Modifier
        .padding(vertical = 5.dp, horizontal = 0.dp)
        .padding(WindowInsets.ime.asPaddingValues())

    MaterialTheme {
        Surface {
            AppDrawer {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(messageViewModel.successMessage!!) { data ->
                            Snackbar(containerColor = Color(0xFF7CB342), snackbarData = data, modifier = snackbarModifier)
                        }
                        SnackbarHost(messageViewModel.errorMessage!!) { data ->
                            Snackbar(containerColor = Color(0xFFE53935), snackbarData = data, modifier = snackbarModifier)
                        }
                        SnackbarHost(messageViewModel.warningMessage!!) { data ->
                            Snackbar(containerColor = Color(0xFFF9A825), snackbarData = data, modifier = snackbarModifier)
                        }
                        SnackbarHost(messageViewModel.infoMessage!!) { data ->
                            Snackbar(containerColor = Color(0xFF00BCD4), snackbarData = data, modifier = snackbarModifier)
                        }
                    },
                    topBar = {
                        AppHeader()
                    },
                    bottomBar = {
                        AppFooter()
                    }
                )
                { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        AppRouter()
                    }
                }
            }
        }
    }
}
