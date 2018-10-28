package tremend.com.moviedb.data.vo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(@PrimaryKey val id: Long, val title: String, val popularity: Float,
                 @SerializedName("original_language") val originalLanguage: String?,
                 @SerializedName("genre_ids") val genreIds: List<Long>,
                 @SerializedName("original_title") val originalTitle: String?,
                 val adult: Boolean, val overview: String?, @SerializedName("release_date") val releaseDate: String?) : Parcelable