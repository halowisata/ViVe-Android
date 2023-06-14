package academy.bangkit.jetvive.ui.screen.profile

import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.LogoutRequest
import academy.bangkit.jetvive.data.source.remote.response.LogoutResponse
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import academy.bangkit.jetvive.ui.common.UiState
import android.util.JsonToken
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _logoutStatus = MutableStateFlow<UiState<LogoutResponse>>(UiState.Loading)
    val logoutStatus: StateFlow<UiState<LogoutResponse>> get() = _logoutStatus

    private val _loginData = MutableStateFlow<UserEntity?>(null)
    val loginData: StateFlow<UserEntity?> get() = _loginData

    private val _userData: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val userData: StateFlow<UiState<UserResponse>> get() = _userData

    fun logout(logoutRequest: LogoutRequest) {
        viewModelScope.launch {
            _logoutStatus.value = UiState.Loading
            val uiState = userRepository.logout(logoutRequest)
            _logoutStatus.value = when(uiState) {
                is UiState.Success -> {
                    userRepository.deleteLogin()
                    UiState.Success(uiState.data)
                }
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
    fun getLogin() {
        viewModelScope.launch {
            userRepository.getLogin().collect { userEntity ->
                _loginData.value = userEntity
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