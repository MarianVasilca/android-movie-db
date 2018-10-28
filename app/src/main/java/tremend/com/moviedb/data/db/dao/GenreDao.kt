package tremend.com.moviedb.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tremend.com.moviedb.data.vo.Genre

/**
 * Interface for database access for [Genre] related operations.
 */
@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: Array<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Genre)

    @Query("SELECT * FROM genres")
    fun listenForItems(): LiveData<List<Genre>>

    @Query("DELETE FROM genres")
    fun deleteItems()
}