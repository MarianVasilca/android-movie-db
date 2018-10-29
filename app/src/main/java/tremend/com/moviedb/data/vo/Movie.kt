package tremend.com.moviedb.data.vo

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import tremend.com.moviedb.BuildConfig

@Entity(tableName = "movies")
@Parcelize
data class Movie(
        @PrimaryKey val id: Long,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("genre_ids") val genreIds: List<Long>,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("vote_average") val voteAverage: Float,
        val title: String,
        val popularity: Float,
        val adult: Boolean,
        val overview: String?
) : Parcelable {

    fun getImageURL(): String = BuildConfig.TMDB_IMAGE_BASE_URL + posterPath

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title && oldItem.overview == newItem.overview &&
                        oldItem.adult == newItem.adult && oldItem.releaseDate == newItem.releaseDate &&
                        oldItem.originalLanguage == newItem.originalLanguage && oldItem.originalTitle == newItem.originalTitle
            }
        }
    }
}