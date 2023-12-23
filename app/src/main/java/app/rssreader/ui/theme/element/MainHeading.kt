package app.rssreader.ui.theme.element

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppMainHeading(text: String) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(0.dp, 4.dp, 0.dp, 4.dp),
    )
    Divider(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 6.dp),
    )
}
