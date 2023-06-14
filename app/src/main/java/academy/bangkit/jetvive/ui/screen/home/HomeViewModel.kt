package academy.bangkit.jetvive.ui.screen.home

import academy.bangkit.jetvive.data.repository.SurveyRepository
import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.response.GetSurveyResponse
import academy.bangkit.jetvive.data.source.remote.response.TouristAttractionsResponse
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val surveyRepository: SurveyRepository,
    private val touristAttractionRepository: TouristAttractionRepository
): ViewModel() {

    private val _uiLoginDataState = MutableStateFlow<UserEntity?>(null)
    val uiLoginDataState: StateFlow<UserEntity?> get() = _uiLoginDataState

    private val _uiUserDataState: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val uiUserDataState: StateFlow<UiState<UserResponse>> get() = _uiUserDataState

    private val _uiTouristAttractionState: MutableStateFlow<UiState<List<TouristAttraction>>> =
        MutableStateFlow(UiState.Loading)
    val uiTouristAttractionState: StateFlow<UiState<List<TouristAttraction>>> get() =
        _uiTouristAttractionState

    private val _uiTouristAttractionDataState: MutableStateFlow<UiState<TouristAttractionsResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiTouristAttractionDataState: StateFlow<UiState<TouristAttractionsResponse>> get() =
        _uiTouristAttractionDataState

    private val _uiSurveyState: MutableStateFlow<UiState<GetSurveyResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiSurveyState: StateFlow<UiState<GetSurveyResponse>> get() = _uiSurveyState

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

    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _uiLoginDataState.value = userEntity
            }
        }
    }

    fun getUser(accessToken: String) {
        viewModelScope.launch {
            _uiUserDataState.value = UiState.Loading
            val uiState = userRepository.getUser(accessToken)
            _uiUserDataState.value = when(uiState) {
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
            _uiTouristAttractionDataState.value = UiState.Loading
            val uiTouristAttractionDataState = touristAttractionRepository.getAllTouristAttractions(accessToken, mood, budget, city)
            _uiTouristAttractionDataState.value = when (uiTouristAttractionDataState) {
                is UiState.Success -> UiState.Success(uiTouristAttractionDataState.data)
                is UiState.Error -> UiState.Error(uiTouristAttractionDataState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}