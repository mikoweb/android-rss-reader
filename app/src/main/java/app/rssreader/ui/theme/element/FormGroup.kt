package app.rssreader.ui.theme.element

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppFormGroup(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.padding(0.dp, 3.dp),
    ) {
        content()
    }
}
