package ro.valentin.movielibrary.presentation.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import ro.valentin.movielibrary.core.Constants
import ro.valentin.movielibrary.data.network.dto.toMovie
import ro.valentin.movielibrary.domain.repository.MovieDbRepository
import javax.inject.Inject

@HiltViewModel
class LatestMoviesViewModel @Inject constructor(
   private val movieDbRepository: MovieDbRepository
) : ViewModel() {
    fun getLatestMovies() = liveData(Dispatchers.IO) {
        Log.d(Constants.LOG_TAG, "latestMoviesViewModel()")
        movieDbRepository.getLatestMovies().collectLatest { pagingDataMovieDto ->
            val pagingDataMovie = pagingDataMovieDto.map { movieDto ->
                movieDto.toMovie()
            }
            emit(pagingDataMovie)
        }
    }.cachedIn(viewModelScope)
}