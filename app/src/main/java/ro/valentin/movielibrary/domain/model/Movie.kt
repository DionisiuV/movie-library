package ro.valentin.movielibrary.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String? = null,
    val rating: Double,
    val releaseDate: String,
)