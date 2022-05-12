package ro.valentin.movielibrary.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import ro.valentin.movielibrary.data.repository.AuthRepositoryImpl
import ro.valentin.movielibrary.domain.repository.AuthRepository
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val isUserAuthenticated get() = authRepository.isUserAuthenticatedInFirebase()
}