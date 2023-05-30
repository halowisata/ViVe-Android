package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.data.repository.MoodRepository
import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.model.mood.Mood
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moodRepository: MoodRepository,
    private val touristAttractionRepository: TouristAttractionRepository
    ): ViewModel() {

    private val _uiMoodState: MutableStateFlow<UiState<List<Mood>>> = MutableStateFlow(UiState.Loading)
    val uiMoodState: StateFlow<UiState<List<Mood>>> get() = _uiMoodState

    private val _uiTouristAttractionState: MutableStateFlow<UiState<List<TouristAttraction>>> =
        MutableStateFlow(UiState.Loading)
    val uiTouristAttractionState: StateFlow<UiState<List<TouristAttraction>>> get() =
        _uiTouristAttractionState

    fun getAllMoods() {
        viewModelScope.launch {
            moodRepository.getAllMoods()
                .catch {
                    _uiMoodState.value = UiState.Error(it.message.toString())
                }
                .collect { moods ->
                    _uiMoodState.value = UiState.Success(moods)
                }
        }
    }

    fun getAllTouristAttractions() {
        viewModelScope.launch {
            touristAttractionRepository.getAllTouristAttractions()
                .catch {
                    _uiTouristAttractionState.value = UiState.Error(it.message.toString())
                }
                .collect { touristAttractions ->
                    _uiTouristAttractionState.value = UiState.Success(touristAttractions)
                }
        }
    }
}