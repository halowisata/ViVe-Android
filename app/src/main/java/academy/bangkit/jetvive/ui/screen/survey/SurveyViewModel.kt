package academy.bangkit.jetvive.ui.screen.survey

import academy.bangkit.jetvive.data.repository.SurveyRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.SurveyRequest
import academy.bangkit.jetvive.data.source.remote.response.PostSurveyResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SurveyViewModel(
    private val userRepository: UserRepository,
    private val surveyRepository: SurveyRepository
    ): ViewModel() {

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

    private val _uiSurveyState = MutableStateFlow<UiState<PostSurveyResponse>>(UiState.Loading)
    val uiSurveyState: StateFlow<UiState<PostSurveyResponse>> get() = _uiSurveyState

    fun addSurvey(accessToken: String, surveyRequest: SurveyRequest) {
        viewModelScope.launch {
            _uiSurveyState.value = UiState.Loading
            val uiState = surveyRepository.addSurvey(accessToken, surveyRequest)
            _uiSurveyState.value = when (uiState) {
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