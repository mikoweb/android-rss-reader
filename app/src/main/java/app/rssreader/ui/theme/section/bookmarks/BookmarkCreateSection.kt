package app.rssreader.ui.theme.section.bookmarks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.rssreader.ui.theme.element.AppFormGroup
import app.rssreader.ui.theme.element.AppMainContainer
import app.rssreader.ui.theme.element.AppMainHeading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppBookmarkCreateSection() {
    AppMainContainer {
        AppMainHeading("Tworzenie zakładki")
        AppFormGroup {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text("Nazwa zakładki") },
                singleLine = true
            )
        }
        AppFormGroup {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = { Text("Adres RSS") },
                placeholder = { Text("Podaj URL kanału RSS...") },
                singleLine = true
            )
        }
        AppFormGroup {
            Button(onClick = {}) {
                Text("Utwórz")
            }
        }
    }
}
