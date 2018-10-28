package tremend.com.moviedb.data.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.vo.Genre
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.data.vo.MovieFilter
import tremend.com.moviedb.utilities.ALL_GENRE_ID
import tremend.com.moviedb.utilities.ALL_GENRE_NAME
import tremend.com.moviedb.utilities.schedulers.IoScheduler
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import tremend.com.moviedb.utilities.schedulers.NetworkScheduler

class MovieRepository constructor(
        private val apiService: ApiService,
        private val database: AppDatabase,
        private val mainScheduler: MainScheduler,
        private val networkScheduler: NetworkScheduler,
        private val ioScheduler: IoScheduler
) {

    fun loadMovies(): LiveData<List<Movie>> {
        return database.movieDao().listenForItems()
    }

    fun searchMovies(filter: MovieFilter): LiveData<List<Movie>> {
        var genreId = ""
        if (filter.genre != null && filter.genre?.id != ALL_GENRE_ID) {
            genreId = filter.genre?.id?.toString() ?: ""
        }

        return database.movieDao().searchItems(filter.title ?: "", genreId)
    }

    fun fetchMovies(): Single<Unit> = Single.create { emitter ->
        apiService.getMovies("popularity.asc")
                .subscribeOn(networkScheduler.asRxScheduler())
                .observeOn(ioScheduler.asRxScheduler())
                .subscribeBy(
                        onSuccess = {
                            database.movieDao().insert(it.results)
                            emitter.onSuccess(Unit)
                        },
                        onError = {
                            emitter.onError(it)
                        }
                )

    }

    fun loadGenres(): LiveData<List<Genre>> {
        return database.genreDao().listenForItems()
    }

    fun fetchGenres(): Single<Unit> = Single.create { emitter ->
        apiService.getGenres()
                .subscribeOn(networkScheduler.asRxScheduler())
                .observeOn(ioScheduler.asRxScheduler())
                .subscribeBy(
                        onSuccess = {
                            database.genreDao().insert(Genre(ALL_GENRE_ID, ALL_GENRE_NAME))
                            database.genreDao().insert(it.genres)
                            emitter.onSuccess(Unit)
                        },
                        onError = {
                            emitter.onError(it)
                        }
                )

    }

}