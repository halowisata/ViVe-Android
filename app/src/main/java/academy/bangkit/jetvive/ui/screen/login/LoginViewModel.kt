package academy.bangkit.jetvive.ui.screen.login

import academy.bangkit.jetvive.data.repository.UserRepository
import androidx.lifecycle.ViewModel

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    suspend fun login(email: String, password: String): Boolean =
        userRepository.login(email, password)
}