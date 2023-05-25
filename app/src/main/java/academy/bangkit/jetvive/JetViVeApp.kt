package academy.bangkit.jetvive

import academy.bangkit.jetvive.ui.screen.splash.SplashScreen
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun JetViVeApp(
    modifier: Modifier = Modifier
) {
    Surface {
        SplashScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun JetViVeAppPreview() {
    JetViVeTheme {
        JetViVeApp()
    }
}