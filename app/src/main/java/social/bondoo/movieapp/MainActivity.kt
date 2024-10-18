package social.bondoo.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import social.bondoo.movieapp.navigation.MovieNavigation
import social.bondoo.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit = { }) {
    MovieAppTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MyApp {
       MovieNavigation()
    }
}