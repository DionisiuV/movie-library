package ro.valentin.movielibrary.presentation.movies

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ro.valentin.movielibrary.core.Constants.IMG_BASE_URL

@BindingAdapter("android:posterPath")
fun setPosterPathToImgView(posterImageView: ImageView, posterPath: String?) {
    posterPath?.let {
        Glide.with(posterImageView.context).load("$IMG_BASE_URL$posterPath").into(posterImageView)
    }

}