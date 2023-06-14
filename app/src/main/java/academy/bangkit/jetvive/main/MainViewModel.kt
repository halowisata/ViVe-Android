package academy.bangkit.jetvive.main

import academy.bangkit.jetvive.data.repository.SurveyRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.response.GetSurveyResponse
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
    private val surveyRepository: SurveyRepository
    ): ViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

    private val _uiUserState: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val uiUserState: StateFlow<UiState<UserResponse>> get() = _uiUserState

    private val _uiSurveyState: MutableStateFlow<UiState<GetSurveyResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiSurveyState: StateFlow<UiState<GetSurveyResponse>> get() = _uiSurveyState

    init {
        viewModelScope.launch {
            delay(2000)
            mutableStateFlow.value = false
        }
    }

    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _uiLoginState.value = userEntity
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
}