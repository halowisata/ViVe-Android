package academy.bangkit.jetvive.ui.screen.detail

import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.SaveTouristAttractionRequest
import academy.bangkit.jetvive.data.source.remote.response.PostSavedTouristAttractionResponse
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val userRepository: UserRepository,
    private val touristAttractionRepository: TouristAttractionRepository
    ): ViewModel() {

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

    private val _uiSaveTouristAttractionState:
            MutableStateFlow<UiState<PostSavedTouristAttractionResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiSaveTouristAttractionState: StateFlow<UiState<PostSavedTouristAttractionResponse>> get() =
        _uiSaveTouristAttractionState

    fun saveTouristAttraction(
        accessToken: String,
        saveTouristAttractionRequest: SaveTouristAttractionRequest
    ) {
        viewModelScope.launch {
            _uiSaveTouristAttractionState.value = UiState.Loading
            val uiState =
                touristAttractionRepository.saveTouristAttraction(
                    accessToken,
                    saveTouristAttractionRequest
                )
            _uiSaveTouristAttractionState.value = when (uiState) {
                is UiState.Success -> UiState.Success(uiState.data)
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }

    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _uiLoginState.value = userEntity
            }
        }
    }
}