package academy.bangkit.jetvive.helper

import academy.bangkit.jetvive.data.source.remote.response.GetSavedTouristAttractionData
import academy.bangkit.jetvive.data.source.remote.response.TouristAttractionData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    var selectedTouristAttractionItem: TouristAttractionData? by mutableStateOf(null)
    var selectedSavedTouristAttractionItem: GetSavedTouristAttractionData?
        by mutableStateOf(null)
}