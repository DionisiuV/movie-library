package ro.valentin.movielibrary.domain.model

data class Movie(
    val title: String,
    val imagePath: String,
    val rating: String,
    val releaseYear: String
)