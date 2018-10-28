package tremend.com.moviedb.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tremend.com.moviedb.data.vo.Movie

/**
 * Interface for database access for [Movie] related operations.
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: Array<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :title|| '%' AND genreIds LIKE '%' ||:genreId|| '%'")
    fun searchItems(title: String, genreId: String): LiveData<List<Movie>>

    @Query("DELETE FROM movies")
    fun deleteItems()
}