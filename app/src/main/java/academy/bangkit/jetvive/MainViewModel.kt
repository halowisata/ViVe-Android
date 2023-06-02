package academy.bangkit.jetvive

import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.ui.common.UiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository): ViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    private val _uiState: MutableStateFlow<UiState<Flow<UserEntity>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Flow<UserEntity>>>
        get() = _uiState

    init {
        viewModelScope.launch {
            delay(2000)
            mutableStateFlow.value = false
        }
    }

    fun getLogin() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(userRepository.getLogin())
    }
}