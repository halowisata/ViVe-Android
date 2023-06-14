package academy.bangkit.jetvive.helper

import academy.bangkit.jetvive.data.source.remote.response.TouristAttractionData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    var selectedItem: TouristAttractionData? by mutableStateOf(null)
}