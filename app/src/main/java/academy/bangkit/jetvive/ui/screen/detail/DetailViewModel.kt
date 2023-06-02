package academy.bangkit.jetvive.ui.screen.detail

import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val touristAttractionRepository: TouristAttractionRepository
    ): ViewModel() {
        private val _uiState: MutableStateFlow<UiState<TouristAttraction>> =
            MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TouristAttraction>> get() = _uiState

    fun getTouristAttractionById(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(
                touristAttractionRepository.getDetailTouristAttractionById(id)
            )
        }
    }
}