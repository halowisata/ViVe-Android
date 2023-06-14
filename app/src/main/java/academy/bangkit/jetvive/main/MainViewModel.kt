package academy.bangkit.jetvive.main

import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository): ViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    private val _loginData = MutableStateFlow<UserEntity?>(null)
    val loginData: StateFlow<UserEntity?> get() = _loginData

    private val _userData: MutableStateFlow<UiState<UserResponse>> = MutableStateFlow(UiState.Loading)
    val userData: StateFlow<UiState<UserResponse>> get() = _userData

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