package ro.valentin.movielibrary.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.valentin.movielibrary.domain.model.Response
import ro.valentin.movielibrary.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl
@Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val oneTapClient: SignInClient,
    @Named("signInRequest") private val beginSignInRequest: BeginSignInRequest,
    @Named("signUpRequest") private val beginSignUpRequest: BeginSignInRequest
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

    override suspend fun oneTapSignUpGoogle() = flow {
        try {
            emit(Response.Loading)
            val result = oneTapClient.beginSignIn(beginSignUpRequest).await()
            emit(Response.Success(result))
        } catch (e: Exception){
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firebaseSignInWithGoogle(authCredential: AuthCredential) = flow {
        try {
            emit(Response.Loading)
            firebaseAuth.signInWithCredential(authCredential).await()
            emit(Response.Success(true))
        } catch (e: Exception){
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firebaseSignOut() = flow {
        try {
            emit(Response.Loading)
            firebaseAuth.signOut()
            oneTapClient.signOut().await()
            emit(Response.Success(true))
        } catch (e: Exception){
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}