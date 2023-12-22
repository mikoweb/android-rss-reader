package app.rssreader.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.rssreader.ui.theme.element.AppRssTextBox
import app.rssreader.ui.theme.layout.AppFooter
import app.rssreader.ui.theme.layout.AppHeader
import app.rssreader.ui.theme.section.AppRssItemsSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AppTheme() {
    MaterialTheme {
        Scaffold(
            topBar = {
                AppHeader()
            },
            bottomBar = {
                AppFooter()
            },
            ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AppRssTextBox()
                AppRssItemsSection()
            }
        }
    }
}
