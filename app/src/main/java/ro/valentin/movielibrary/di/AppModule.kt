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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.valentin.movielibrary.R
import ro.valentin.movielibrary.core.Constants.BASE_URL
import ro.valentin.movielibrary.data.network.MovieDbApi
import ro.valentin.movielibrary.data.repository.AuthRepositoryImpl
import ro.valentin.movielibrary.data.repository.MovieDbRepositoryImpl
import ro.valentin.movielibrary.domain.repository.AuthRepository
import ro.valentin.movielibrary.domain.repository.MovieDbRepository
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideOneTapClient(application: Application):
            SignInClient = Identity.getSignInClient(
        application.applicationContext
    )

    @Provides
    @Named("signUpRequest")
    fun provideSignUpRequest(application: Application): BeginSignInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId(application.getString(R.string.web_client_id))
                // Show all accounts on the device.
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()

    @Provides
    @Named("signInRequest")
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
        oneTapClient: SignInClient,
        @Named("signInRequest") beginSignInRequest: BeginSignInRequest,
        @Named("signUpRequest") beginSignUpRequest: BeginSignInRequest
    ): AuthRepository = AuthRepositoryImpl(
            firebaseAuth = firebaseAuth,
            oneTapClient = oneTapClient,
            beginSignInRequest = beginSignInRequest,
            beginSignUpRequest = beginSignUpRequest
        )

    @Provides
    fun provideMovieDbApi(): MovieDbApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieDbApi::class.java)

    @Provides
    fun provideMovieDbRepository(
        movieDbApi: MovieDbApi
    ): MovieDbRepository = MovieDbRepositoryImpl(
        movieDbApi = movieDbApi
    )

}