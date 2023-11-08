package app.rssreader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import app.rssreader.ui.theme.AppTheme
import app.rssreader.ui.theme.element.RssTextBoxViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var rssTextBoxViewModel: RssTextBoxViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        Log.i("Sample", "LOAD ACTIVITY")

        setContent {
            AppLayout()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppLayout() {
    AppTheme()
}
