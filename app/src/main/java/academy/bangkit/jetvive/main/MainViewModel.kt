package academy.bangkit.jetvive.main

import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
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

    private val _uiLoginState = MutableStateFlow<UserEntity?>(null)
    val uiLoginState: StateFlow<UserEntity?> get() = _uiLoginState

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
                _uiLoginState.value = userEntity
            }
        }
    }
}