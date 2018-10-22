package tremend.com.moviedb.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey val id: Long,
        val name: String
)