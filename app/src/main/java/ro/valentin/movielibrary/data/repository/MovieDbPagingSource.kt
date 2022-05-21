package ro.valentin.movielibrary.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import retrofit2.await
import ro.valentin.movielibrary.core.Constants.API_KEY_VALUE
import ro.valentin.movielibrary.core.Constants.LOG_TAG
import ro.valentin.movielibrary.data.network.MovieDbApi
import ro.valentin.movielibrary.data.network.dto.MovieDto
import ro.valentin.movielibrary.domain.model.Movie
import java.io.IOException

class MovieDbPagingSource(
    private val movieDbApi: MovieDbApi) : PagingSource<Int, MovieDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
       return try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = movieDbApi.getLatestMovies(API_KEY_VALUE, nextPageNumber).await()

            return LoadResult.Page(
                data = response.movieDtoList,
                prevKey = null, // Only paging forward.
                nextKey = if (response.movieDtoList.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
           // IOException for network failures.
            LoadResult.Error(e)
       } catch (e: HttpException) {
           // HttpException for any non-2xx HTTP status codes.
            LoadResult.Error(e)
       }
    }
}