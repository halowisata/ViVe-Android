package academy.bangkit.jetvive.ui.screen.bookmark

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.model.tourist_attraction.FakeTouristAttractionDataSource
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.TouristAttractionItem
import academy.bangkit.jetvive.ui.screen.home.HomeViewModel
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BookmarkScreen(
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
                BookmarkContent(uiTouristAttractionState.data)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun BookmarkContent(
    touristAttractions: List<TouristAttraction>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
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
                        text = "Saved Item",
                        fontWeight = FontWeight.Medium,
                        fontSize = 19.sp,
                        lineHeight = 29.sp,
                        letterSpacing = .15.sp
                    )
                }
            }
            items(touristAttractions) { touristAttraction ->
                TouristAttractionItem(
                    image = R.drawable.jetpack_compose,
                    name = touristAttraction.name,
                    onClick = {}
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
fun BookmarkContentPreview() {
    JetViVeTheme {
        BookmarkContent(
            FakeTouristAttractionDataSource.dummyTouristAttractions
        )
    }
}