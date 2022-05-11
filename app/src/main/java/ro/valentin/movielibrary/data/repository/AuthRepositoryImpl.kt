package ro.valentin.movielibrary.data.repository

import com.google.firebase.auth.FirebaseAuth
import ro.valentin.movielibrary.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean = firebaseAuth.currentUser != null
}