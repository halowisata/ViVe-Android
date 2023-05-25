package academy.bangkit.jetvive.ui.screen.splash

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen() {
    SplashContent()
}

@Composable
fun SplashContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.jetpack_compose),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SplashContentPreview() {
    JetViVeTheme {
        SplashContent()
    }
}