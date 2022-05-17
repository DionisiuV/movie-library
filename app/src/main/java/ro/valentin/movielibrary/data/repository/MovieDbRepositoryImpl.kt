package ro.valentin.movielibrary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ro.valentin.movielibrary.data.network.MovieDbApi
import ro.valentin.movielibrary.data.network.dto.MovieDto
import ro.valentin.movielibrary.domain.repository.MovieDbRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDbRepositoryImpl @Inject constructor(
    private val movieDbApi: MovieDbApi
): MovieDbRepository {
    override suspend fun getMovies(query: String) = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        MovieDbPagingSource(movieDbApi, query)
    }.flow
}