package academy.bangkit.jetvive.ui.screen.onboarding

import academy.bangkit.jetvive.data.repository.OnboardingRepository
import academy.bangkit.jetvive.model.onboarding.Onboarding
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OnboardingViewModel(private val onboardingRepository: OnboardingRepository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Onboarding>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Onboarding>>> get() = _uiState

    fun getAllOnboardingData() {
        viewModelScope.launch {
            onboardingRepository.getAllOnboardingData()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { onboarding ->
                    _uiState.value = UiState.Success(onboarding)
                }
        }
    }
}