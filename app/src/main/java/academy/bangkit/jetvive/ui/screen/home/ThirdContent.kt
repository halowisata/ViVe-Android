package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.model.tourist_attraction.FakeTouristAttractionDataSource
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.SearchBar
import academy.bangkit.jetvive.ui.components.TouristAttractionItem
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

@Composable
fun ThirdContent(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    viewModel.uiTouristAttractionState.collectAsState(initial = UiState.Loading).value.let { uiTouristAttractionState ->
        when (uiTouristAttractionState) {
            is UiState.Loading -> {
                viewModel.getAllTouristAttractions()
            }
            is UiState.Success -> {
                Content(uiTouristAttractionState.data)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun Content(
    touristAttractions: List<TouristAttraction>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.mood_happy),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
            )
            Text(
                text = "Happy",
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null)
            }
        }
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
        SearchBar()
        Divider(color = MaterialTheme.colorScheme.primary, thickness = 1.dp)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            header {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = "Good Morning, John Doe",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        lineHeight = 30.sp,
                        letterSpacing = .15.sp
                    )
                    Text(
                        text = "We set up yout based on your moods. Adjust your travelling based on your energy.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = .25.sp
                    )
                    Text(
                        text = "Top Recommendation",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        lineHeight = 30.sp,
                        letterSpacing = .15.sp
                    )
                }
            }
            items(touristAttractions) { touristAttraction ->
                TouristAttractionItem(
                    image = R.drawable.jetpack_compose,
                    title = touristAttraction.name
                )
            }
        }
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Preview(showBackground = true)
@Composable
fun ThirdContentPreview() {
    JetViVeTheme {
        Content(
            FakeTouristAttractionDataSource.dummyTouristAttractions
        )
    }
}