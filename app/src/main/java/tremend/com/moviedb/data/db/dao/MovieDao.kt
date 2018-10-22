package tremend.com.moviedb.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
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

    @Query("SELECT * FROM movies")
    fun listenForItems(): Flowable<List<Movie>>

    @Query("DELETE FROM movies")
    fun deleteItems()
}