package ro.valentin.movielibrary.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.valentin.movielibrary.domain.model.Response
import ro.valentin.movielibrary.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl
@Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val oneTapClient: SignInClient,
    private val beginSignInRequest: BeginSignInRequest
    ): AuthRepository {
    override fun isUserAuthenticatedInFirebase(): Boolean = firebaseAuth.currentUser != null

    override suspend fun oneTapSignInGoogle() = flow {
        try {
            emit(Response.Loading)
            val result = oneTapClient.beginSignIn(beginSignInRequest).await()
            emit(Response.Success(result))
        } catch (e: Exception){
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}