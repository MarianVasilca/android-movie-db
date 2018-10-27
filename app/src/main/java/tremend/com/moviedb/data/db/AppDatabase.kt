package tremend.com.moviedb.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tremend.com.moviedb.data.db.dao.MovieDao
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.utilities.DATABASE_NAME

/**
 * The Room database for this app
 */
@Database(
        entities = [Movie::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(LongListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .build()
        }
    }
}