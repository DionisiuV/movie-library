package ro.valentin.movielibrary.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ro.valentin.movielibrary.data.network.dto.MovieDto

interface MovieDbRepository {
    suspend fun getLatestMovies(): Flow<PagingData<MovieDto>>
}