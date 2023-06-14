package academy.bangkit.jetvive.ui.screen.profile

import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.LogoutRequest
import academy.bangkit.jetvive.data.source.remote.response.LogoutResponse
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _uiLogoutState = MutableStateFlow<UiState<LogoutResponse>>(UiState.Loading)
    val uiLogoutState: StateFlow<UiState<LogoutResponse>> get() = _uiLogoutState

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

    private val _uiUserState: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val uiUserState: StateFlow<UiState<UserResponse>> get() = _uiUserState

    fun logout(logoutRequest: LogoutRequest) {
        viewModelScope.launch {
            _uiLogoutState.value = UiState.Loading
            val uiState = userRepository.logout(logoutRequest)
            _uiLogoutState.value = when(uiState) {
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
}