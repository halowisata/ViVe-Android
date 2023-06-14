package academy.bangkit.jetvive.ui.screen.bookmark

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.helper.SharedViewModel
import academy.bangkit.jetvive.helper.ViewModelFactory
import academy.bangkit.jetvive.ui.common.UiState
import academy.bangkit.jetvive.ui.components.TouristAttractionItem
import academy.bangkit.jetvive.ui.theme.JetViVeTheme
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BookmarkScreen(
    sharedViewModel: SharedViewModel,
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    BookmarkContent(
        navigateToDetail = navigateToDetail,
        sharedViewModel = sharedViewModel
    )
}

@Composable
fun BookmarkContent(
    navigateToDetail: () -> Unit,
    sharedViewModel: SharedViewModel,
    viewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory.getInstance(context = LocalContext.current)
    ),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        viewModel.getLogin()

        val uiLoginState by viewModel.uiLoginState.collectAsState()

        viewModel.uiSavedTouristAttractionsState.collectAsState(initial = UiState.Loading).value.let { uiSavedTouristAttractionState ->
            when (uiSavedTouristAttractionState) {
                is UiState.Loading -> {
                    viewModel.getSavedTouristAttractions(uiLoginState?.accessToken.toString())
                }
                is UiState.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(160.dp),
                        contentPadding = PaddingValues(
                            vertical = 16.dp,
                            horizontal = 20.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        header {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.saved_item),
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        items(uiSavedTouristAttractionState.data.data) { touristAttraction ->
                            TouristAttractionItem(
                                image = R.drawable.jetpack_compose,
                                name = touristAttraction.name,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .clickable {
                                        sharedViewModel.selectedSavedTouristAttractionItem = touristAttraction
                                        navigateToDetail()
                                    }
                            )
                        }
                    }
                }
                is UiState.Error -> {}
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
            sharedViewModel = SharedViewModel(),
            navigateToDetail = {}
        )
    }
}