package ro.valentin.movielibrary.presentation.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ro.valentin.movielibrary.R
import ro.valentin.movielibrary.core.Constants
import ro.valentin.movielibrary.domain.model.Movie
import ro.valentin.movielibrary.presentation.auth.AuthViewModel

@AndroidEntryPoint
class LatestMoviesFragment : Fragment(R.layout.fragment_latest_movies), LatestMoviesAdapter.OnMovieClickListener {
    private val viewModel by activityViewModels<LatestMoviesViewModel>()
    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var latestMoviesAdapter: LatestMoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(Constants.LOG_TAG, "latestMoviesFragment")

        initMovieRecyclerView(view)
        initLatestMoviesAdapter()
        getLatestMovies()
        setMovieAdapter()
    }

    private fun initMovieRecyclerView(view: View) {
        usersRecyclerView = view.findViewById(R.id.moviesRecyclerView)
    }

    private fun initLatestMoviesAdapter() {
        latestMoviesAdapter = LatestMoviesAdapter(this)
    }

    private fun getLatestMovies() {
         viewModel.getLatestMovies().observe(viewLifecycleOwner) {  moviePagingData ->
             viewLifecycleOwner.lifecycleScope.launch {
                 latestMoviesAdapter.submitData(moviePagingData)
             }
        }
    }

    private fun setMovieAdapter() {
        usersRecyclerView.adapter = latestMoviesAdapter
    }

    override fun onMovieClick(movie: Movie) {
        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
    }
}