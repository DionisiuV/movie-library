package ro.valentin.movielibrary.domain.repository


import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow
import ro.valentin.movielibrary.domain.model.Response

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun oneTapSignInGoogle(): Flow<Response<BeginSignInResult>>
    suspend fun oneTapSignUpGoogle(): Flow<Response<BeginSignInResult>>
    suspend fun firebaseSignInWithGoogle(authCredential: AuthCredential): Flow<Response<Boolean>>
    suspend fun firebaseSignOut(): Flow<Response<Boolean>>
}