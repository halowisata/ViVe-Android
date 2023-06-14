package academy.bangkit.jetvive.ui.screen.bookmark

import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.response.GetSavedTouristAttractionsResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val userRepository: UserRepository,
    private val touristAttractionRepository: TouristAttractionRepository
    ): ViewModel() {

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

    private val _uiSavedTouristAttractionsState: MutableStateFlow<UiState<GetSavedTouristAttractionsResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiSavedTouristAttractionsState: StateFlow<UiState<GetSavedTouristAttractionsResponse>>
        get() =
        _uiSavedTouristAttractionsState

    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _uiLoginState.value = userEntity
            }
        }
    }

    fun getSavedTouristAttractions(accessToken: String) {
        viewModelScope.launch {
            _uiSavedTouristAttractionsState.value = UiState.Loading
            val uiState =
                touristAttractionRepository.getSavedTouristAttractions(accessToken)
            _uiSavedTouristAttractionsState.value = when (uiState) {
                is UiState.Success -> UiState.Success(uiState.data)
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}