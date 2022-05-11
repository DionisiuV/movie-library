package ro.valentin.movielibrary.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ro.valentin.movielibrary.data.repository.AuthRepositoryImpl
import ro.valentin.movielibrary.domain.repository.AuthRepository


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)
}