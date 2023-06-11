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

    private val _loginStatus = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading)
    val loginStatus: StateFlow<UiState<LoginResponse>> get() = _loginStatus

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loginStatus.value = UiState.Loading
            val uiState = userRepository.login(loginRequest)
            _loginStatus.value = when(uiState) {
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