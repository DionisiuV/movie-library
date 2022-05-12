package ro.valentin.movielibrary.di

import android.app.Application
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.valentin.movielibrary.R
import ro.valentin.movielibrary.data.repository.AuthRepositoryImpl
import ro.valentin.movielibrary.domain.repository.AuthRepository


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideOneTapClient(application: Application):
            SignInClient = Identity.getSignInClient(
        application.applicationContext
    )

    @Provides
    fun provideSignInRequest(application: Application): BeginSignInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(application.getString(R.string.web_client_id))
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(true)
                .build())
        .build()

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        beginSignInRequest: BeginSignInRequest,
        oneTapClient: SignInClient
    ) :
            AuthRepository = AuthRepositoryImpl(
        firebaseAuth = firebaseAuth,
        beginSignInRequest = beginSignInRequest,
        oneTapClient = oneTapClient)
}