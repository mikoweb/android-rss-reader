package app.rssreader.ui.theme.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.rssreader.application.logic.viewmodel.ViewModelMap

@Composable
@Preview
fun AppRssItemsSection() {
    val viewModel = ViewModelMap.get(RssItemsSectionViewModel::class.java) as RssItemsSectionViewModel
    val details = viewModel.feedObject?.details
    val items = viewModel.feedObject?.items

    if (details != null) {
        Column(
            modifier = androidx.compose.ui.Modifier
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
