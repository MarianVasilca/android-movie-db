package tremend.com.moviedb.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(@PrimaryKey val id: Long, val title: String, val popularity: Float,
                 @SerializedName("original_language") val originalLanguage: String?,
                 @SerializedName("genre_ids") val genreIds: List<Long>,
                 @SerializedName("original_title") val originalTitle: String?,
                 val adult: Boolean, val overview: String?, val releaseDate: String?)