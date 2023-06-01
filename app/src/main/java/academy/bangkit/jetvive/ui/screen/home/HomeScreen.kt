package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.R
import academy.bangkit.jetvive.model.tourist_attraction.FakeTouristAttractionDataSource
import academy.bangkit.jetvive.ui.screen.bookmark.BookmarkContent
import academy.bangkit.jetvive.ui.screen.detail.DetailContent
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    BookmarkContent(touristAttractions = FakeTouristAttractionDataSource.dummyTouristAttractions)
}