package ro.valentin.movielibrary.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ro.valentin.movielibrary.core.Constants
import ro.valentin.movielibrary.data.network.MovieDbApi
import ro.valentin.movielibrary.data.network.dto.MovieDto
import ro.valentin.movielibrary.domain.repository.MovieDbRepository
import javax.inject.Inject
import javax.inject.Singleton

class MovieDbRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi
): MovieDbRepository {
    override suspend fun getLatestMovies() = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        Log.d(Constants.LOG_TAG, "movieDbRepositoryImpl()")
        MovieDbPagingSource(movieDbApi)
    }.flow
}