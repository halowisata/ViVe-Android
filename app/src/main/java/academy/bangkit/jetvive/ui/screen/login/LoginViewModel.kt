package academy.bangkit.jetvive.ui.screen.login

import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.LoginRequest
import academy.bangkit.jetvive.data.source.remote.response.LoginResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<LoginResponse>> get() = _uiState

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val uiState = userRepository.login(loginRequest)
            _uiState.value = when(uiState) {
                is UiState.Success -> {
                    userRepository.setLogin(UserEntity(
                        uiState.data.data.accessToken,
                        uiState.data.data.refreshToken
                    ))
                    UiState.Success(uiState.data)
                }
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}