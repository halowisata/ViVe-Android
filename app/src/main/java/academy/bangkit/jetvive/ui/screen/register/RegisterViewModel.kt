package academy.bangkit.jetvive.ui.screen.register

import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.data.source.remote.response.RegisterResponse
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _registrationStatus = MutableStateFlow<UiState<RegisterResponse>>(UiState.Loading)
    val registrationStatus: StateFlow<UiState<RegisterResponse>> get() = _registrationStatus

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _registrationStatus.value = UiState.Loading
            val uiState = userRepository.register(registerRequest)
            _registrationStatus.value = when (uiState) {
                is UiState.Success -> UiState.Success(uiState.data)
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}