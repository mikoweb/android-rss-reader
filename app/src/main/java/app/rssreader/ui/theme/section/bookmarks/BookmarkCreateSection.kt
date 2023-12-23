package app.rssreader.ui.theme.section.bookmarks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import app.rssreader.application.logic.viewmodel.ViewModelMap
import app.rssreader.ui.theme.MessageViewModel
import app.rssreader.ui.theme.element.AppFormGroup
import app.rssreader.ui.theme.element.AppMainContainer
import app.rssreader.ui.theme.element.AppMainHeading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppBookmarkCreateSection() {
    val viewModel = ViewModelMap.get(BookmarkCreateViewModel::class.java) as BookmarkCreateViewModel
    val messageViewModel = ViewModelMap.get(MessageViewModel::class.java) as MessageViewModel

    AppMainContainer {
        AppMainHeading("Tworzenie zakładki")
        AppFormGroup {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { viewModel.setTouchedValue(true) },
                value = viewModel.name,
                onValueChange = { viewModel.updateName(it) },
                label = { Text("Nazwa zakładki") },
                singleLine = true,
                isError = !viewModel.nameIsValid && viewModel.isTouched
            )
        }
        AppFormGroup {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { viewModel.setTouchedValue(true) },
                value = viewModel.url,
                onValueChange = { viewModel.updateUrl(it) },
                label = { Text("Adres RSS") },
                placeholder = { Text("Podaj URL kanału RSS...") },
                singleLine = true,
                isError = !viewModel.urlIsValid && viewModel.isTouched
            )
        }
        AppFormGroup {
            Button(
                enabled = viewModel.isValid,
                onClick = {
                    try {
                        viewModel.submit()
                        messageViewModel.createSuccess("Utworzono zakładkę")
                    } catch (throwable: Throwable) {
                        messageViewModel.createError("Nie udało się zapisać zakładki!")
                        viewModel.reset()
                    }
                }
            ) {
                Text("Utwórz")
            }
        }
    }
}
