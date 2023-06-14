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

    private val _loginData = MutableStateFlow<UserEntity?>(null)
    val loginData: StateFlow<UserEntity?> get() = _loginData

    private val _userData: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val userData: StateFlow<UiState<UserResponse>> get() = _userData

    private val _uiSurveyState: MutableStateFlow<UiState<GetSurveyResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiSurveyState: StateFlow<UiState<GetSurveyResponse>> get() = _uiSurveyState

    init {
        viewModelScope.launch {
            delay(2000)
            mutableStateFlow.value = false
        }

        getLogin()
    }

    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _loginData.value = userEntity
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
            _userData.value = UiState.Loading
            val uiState = userRepository.getUser(accessToken)
            _userData.value = when(uiState) {
                is UiState.Success -> UiState.Success(uiState.data)
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}