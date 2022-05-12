package ro.valentin.movielibrary.domain.repository


import com.google.android.gms.auth.api.identity.BeginSignInResult
import kotlinx.coroutines.flow.Flow
import ro.valentin.movielibrary.domain.model.Response

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    suspend fun oneTapSignInGoogle(): Flow<Response<BeginSignInResult>>
}