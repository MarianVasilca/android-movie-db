package tremend.com.moviedb.data.vo

import android.os.Parcelable
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
}