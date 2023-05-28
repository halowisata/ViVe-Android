package academy.bangkit.jetvive.ui.components

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoardingItem(
    image: Int,
    headline: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 75.dp),
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(R.string.onboarding_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(250.dp)
        )
        Text(
            text = headline,
            fontSize = 24.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.15.sp,
            fontWeight = FontWeight.W500,
            modifier = modifier,
            textAlign = TextAlign.Center,
        )
        Text(
            text = body,
            fontSize = 16.sp,
            lineHeight = 30.sp,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight.W400,
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingItemPreview() {
    JetViVeTheme {
        OnBoardingItem(
            image = R.drawable.jetpack_compose,
            headline = stringResource(R.string.headline_onboarding),
            body = stringResource(R.string.body_onboarding),
        )
    }
}