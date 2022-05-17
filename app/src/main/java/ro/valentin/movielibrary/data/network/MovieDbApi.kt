package ro.valentin.movielibrary.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ro.valentin.movielibrary.core.Constants.LATEST_MOVIES_URI
import ro.valentin.movielibrary.data.network.dto.MoviesDto

// example request: https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>&language=en-US&page=1

interface MovieDbApi {
    @GET(LATEST_MOVIES_URI)
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int): Call<MoviesDto>
}