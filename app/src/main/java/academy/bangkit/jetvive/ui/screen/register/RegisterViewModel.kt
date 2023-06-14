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
    private val _uiState = MutableStateFlow<UiState<RegisterResponse>>(UiState.Loading)
    val uiState: StateFlow<UiState<RegisterResponse>> get() = _uiState

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val uiState = userRepository.register(registerRequest)
            _uiState.value = when (uiState) {
                is UiState.Success -> UiState.Success(uiState.data)
                is UiState.Error -> UiState.Error(uiState.errorMessage)
                UiState.Loading -> UiState.Loading
            }
        }
    }
}