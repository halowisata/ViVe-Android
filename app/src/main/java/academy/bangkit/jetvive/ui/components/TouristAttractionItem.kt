package academy.bangkit.jetvive.ui.components

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TouristAttractionItem(
    image: Int,
    name: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick("tourist_attraction-1") }
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(175.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TouristAttractionItemPreview() {
    JetViVeTheme {
        TouristAttractionItem(
            image = R.drawable.jetpack_compose,
            name = stringResource(R.string.tourist_attraction_name),
            onClick = {}
        )
    }
}