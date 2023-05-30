package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.data.repository.MoodRepository
import academy.bangkit.jetvive.model.mood.Mood
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val moodRepository: MoodRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Mood>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Mood>>> get() = _uiState

    fun getAllMoods() {
        viewModelScope.launch {
            moodRepository.getAllMoods()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { moods ->
                    _uiState.value = UiState.Success(moods)
                }
        }
    }
}