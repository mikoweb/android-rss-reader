package app.rssreader.ui.theme.section

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.rssreader.application.logic.viewmodel.ViewModelMap
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = details.image ?: "",
                        contentDescription = null,
                    )
                    Text(
                        details.title ?: "",
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                ) {
                    Text(
                        details.description ?: "",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            items?.forEach { item ->
                val context = LocalContext.current
                val webIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(item.link ?: "")
                )

                ElevatedCard(
                    onClick = { context.startActivity(webIntent) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp, 8.dp),
                ) {
                    Row {
                        AsyncImage(
                            model = item.image ?: "",
                            contentDescription = null,
                            modifier = Modifier
                                .width(128.dp),
                        )
                        Text(
                            modifier = Modifier
                                .padding(12.dp),
                            text = item.title ?: ""
                        )
                    }
                }
            }
        }
    }
}
