package ro.valentin.movielibrary.data.network

import android.util.Log
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ro.valentin.movielibrary.core.Constants
import ro.valentin.movielibrary.core.Constants.LATEST_MOVIES_URI
import ro.valentin.movielibrary.data.network.dto.MoviesDto

// example request: https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>&page=1

interface MovieDbApi {
    @GET(LATEST_MOVIES_URI)
    fun getLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): Call<MoviesDto>
}