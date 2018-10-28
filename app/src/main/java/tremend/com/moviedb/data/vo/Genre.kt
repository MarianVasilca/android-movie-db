package tremend.com.moviedb.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "genres")
data class Genre(@PrimaryKey val id: Long, val name: String) {
    override fun toString(): String {
        return name
    }
}