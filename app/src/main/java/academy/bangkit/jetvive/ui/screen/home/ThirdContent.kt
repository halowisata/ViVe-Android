package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.ui.components.SearchBar
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ThirdContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(text = "Good Morning, John Doe")
        Text(text = "We set up yout based on your moods. Adjust your travelling based on your energy.")
        SearchBar()
        Text(text = "Top Recommendation")
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdContentPreview() {
    JetViVeTheme {
        ThirdContent()
    }
}