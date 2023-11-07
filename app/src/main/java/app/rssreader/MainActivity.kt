package app.rssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.rssreader.application.MainService
import app.rssreader.ui.theme.AppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject lateinit var main: MainService

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MainApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContent {
            AppLayout()
            main.main()
        }
    }
}

@Composable
fun Hello(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AppLayout() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            Hello("Android")
        }
    }
}
