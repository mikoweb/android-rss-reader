package app.rssreader.ui.theme.element

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.rssreader.application.logic.viewmodel.ViewModelMap
import app.rssreader.ui.theme.MessageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppRssTextBox() {
    val viewModel = ViewModelMap.get(RssTextBoxViewModel::class.java) as RssTextBoxViewModel
    val messageViewModel = ViewModelMap.get(MessageViewModel::class.java) as MessageViewModel

    TextField(
        modifier = Modifier
            .padding(8.dp, 16.dp)
            .fillMaxWidth(),
        value = viewModel.url,
        onValueChange = {
            try {
                viewModel.updateUrl(it)
                messageViewModel.closeAll()
            } catch (exception: IllegalArgumentException) {
                messageViewModel.createError("Nieprawidłowy adres!")
            } catch (throwable: Throwable) {
                messageViewModel.createError("Nie udało się pobrać RSS!")
            }
        },
        label = { Text("Adres RSS") },
        placeholder = { Text("Podaj URL kanału RSS...") },
        singleLine = true
    )
}
