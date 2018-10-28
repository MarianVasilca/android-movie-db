package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.data.vo.Genre
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.data.vo.Status
import tremend.com.moviedb.utilities.schedulers.MainScheduler

class MovieViewModel(
        app: Application,
        private val repository: MovieRepository,
        private val mainScheduler: MainScheduler
) : BaseAndroidViewModel(app, mainScheduler) {

    private val compositeDisposable = CompositeDisposable()
    private var fetchMoviesDisposable: Disposable? = null
    private var fetchGenresDisposable: Disposable? = null

    val genres = repository.loadGenres()
    val selectedGenre = MutableLiveData<Genre>()

    fun loadMovies(): LiveData<List<Movie>> {
        return repository.loadMovies()
    }

    fun fetchMovies() {
        fetchMoviesDisposable?.dispose()
        fetchMoviesDisposable = repository.fetchMovies()
                .doOnSubscribe { setStatus(Status.LOADING) }
                .subscribe({ setStatus(Status.SUCCESS) }, { setErrorThrowable(it) })
        compositeDisposable.add(fetchMoviesDisposable!!)
    }

    fun loadGenres(): LiveData<List<Genre>> {
        return repository.loadGenres()
    }

    fun fetchGenres() {
        fetchGenresDisposable?.dispose()
        fetchGenresDisposable = repository.fetchGenres()
                .doOnSubscribe { Timber.d("Loading") }
                .subscribe({
                    Timber.d("Success fetch")
                }, {
                    Timber.d(it)
                })
        compositeDisposable.add(fetchGenresDisposable!!)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}