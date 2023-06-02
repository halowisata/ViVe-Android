package academy.bangkit.jetvive.ui.components

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoodItem(
    color: Color,
    image: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(175.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = color)
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp)
            )
            Text(
                text = text,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                lineHeight = 30.sp,
                letterSpacing = .15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodItemPreview() {
    JetViVeTheme {
        MoodItem(
            color = Color(0xFFE5F4F6),
            image = R.drawable.jetpack_compose,
            text = stringResource(R.string.mood_name),
            onClick = {}
        )
    }
}