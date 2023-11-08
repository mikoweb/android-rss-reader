package app.rssreader.ui.theme.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.rssreader.application.logic.viewmodel.ViewModelMap

@Composable
@Preview
fun AppRssItemsSection() {
    val viewModel = ViewModelMap.get(RssItemsSectionViewModel::class.java) as RssItemsSectionViewModel
    val details = viewModel.feedObject?.details
    val items = viewModel.feedObject?.items
    val loading = viewModel.loading

    if (loading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
            )
        }
    }

    if (details != null) {
        Column(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Text(details.title ?: "")
            Text(details.description ?: "")
            Text(details.image ?: "")

            if (items != null) {
                Column {
                    items.forEach { item ->
                        Text(item.title ?: "")
                    }
                }
            }
        }
    }
}
