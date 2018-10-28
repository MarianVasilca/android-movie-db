package tremend.com.moviedb.data.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import tremend.com.moviedb.data.api.ApiService
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.vo.Genre
import tremend.com.moviedb.data.vo.Movie
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

    fun fetchMovies(): Single<Unit> = Single.create { emitter ->
        apiService.getMovies("popularity.desc")
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
                            database.genreDao().insert(it.genres)
                            emitter.onSuccess(Unit)
                        },
                        onError = {
                            emitter.onError(it)
                        }
                )

    }

}