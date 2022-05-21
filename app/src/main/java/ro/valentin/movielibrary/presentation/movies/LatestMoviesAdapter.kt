package ro.valentin.movielibrary.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ro.valentin.movielibrary.databinding.MovieDataBinding
import ro.valentin.movielibrary.domain.model.Movie
import ro.valentin.movielibrary.presentation.movies.LatestMoviesAdapter.MovieViewHolder
import ro.valentin.movielibrary.BR

private object diffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class LatestMoviesAdapter(
    private val onMovieClickListener: OnMovieClickListener
) : PagingDataAdapter<Movie, MovieViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = MovieDataBinding.inflate(layoutInflater, parent, false)

        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

    inner class MovieViewHolder(private val dataBinding: ViewDataBinding) : ViewHolder(dataBinding.root) {
        fun bind(movie: Movie?) {
            dataBinding.setVariable(BR.movie, movie)
            dataBinding.setVariable(BR.onMovieClickListener, onMovieClickListener)
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}
