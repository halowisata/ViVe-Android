package academy.bangkit.jetvive.helper

import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val touristAttractionRepository: TouristAttractionRepository
    ): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}