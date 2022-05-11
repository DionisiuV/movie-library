package ro.valentin.movielibrary.domain.repository

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
}