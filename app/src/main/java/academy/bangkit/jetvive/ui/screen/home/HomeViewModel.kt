package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.data.repository.SurveyRepository
import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.response.GetSurveyResponse
import academy.bangkit.jetvive.data.source.remote.response.TouristAttractionsResponse
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val surveyRepository: SurveyRepository,
    private val touristAttractionRepository: TouristAttractionRepository
): ViewModel() {

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

    private val _uiUserState: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val uiUserState: StateFlow<UiState<UserResponse>> get() = _uiUserState

    private val _uiTouristAttractionsState: MutableStateFlow<UiState<TouristAttractionsResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiTouristAttractionsState: StateFlow<UiState<TouristAttractionsResponse>> get() =
        _uiTouristAttractionsState

    private val _uiSurveyState: MutableStateFlow<UiState<GetSurveyResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiSurveyState: StateFlow<UiState<GetSurveyResponse>> get() = _uiSurveyState

    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _uiLoginState.value = userEntity
            }
        }
    }

    fun getUser(accessToken: String) {
        viewModelScope.launch {
            _uiUserState.value = UiState.Loading
            val uiState = userRepository.getUser(accessToken)
            _uiUserState.value = when(uiState) {
                is UiState.Success -> UiState.Success(uiState.data)
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }

    fun getSurvey(accessToken: String) {
        viewModelScope.launch {
            _uiSurveyState.value = UiState.Loading
            val uiSurveyState = surveyRepository.getSurvey(accessToken)
            _uiSurveyState.value = when (uiSurveyState) {
                is UiState.Success -> UiState.Success(uiSurveyState.data)
                is UiState.Error -> UiState.Error(uiSurveyState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }

    fun getAllTouristAttractions(accessToken: String, mood: String, budget: String, city: String) {
        viewModelScope.launch {
            _uiTouristAttractionsState.value = UiState.Loading
            val uiTouristAttractionsState =
                touristAttractionRepository.getAllTouristAttractions(
                    accessToken,
                    mood,
                    budget,
                    city
                )
            _uiTouristAttractionsState.value = when (uiTouristAttractionsState) {
                is UiState.Success -> UiState.Success(uiTouristAttractionsState.data)
                is UiState.Error -> UiState.Error(uiTouristAttractionsState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}