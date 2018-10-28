package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.data.vo.Movie

class MovieViewModel(
        app: Application,
        private val repository: MovieRepository
) : BaseAndroidViewModel(app) {

    private val compositeDisposable = CompositeDisposable()
    private var fetchMoviesDisposable: Disposable? = null

    fun loadMovies(): LiveData<List<Movie>> {
        return repository.loadMovies()
    }

    fun fetchMovies() {
        fetchMoviesDisposable?.dispose()
        fetchMoviesDisposable = repository.fetchMovies()
                .doOnSubscribe { Timber.d("Loading") }
                .subscribe({
                    Timber.d("Success fetch")
                }, {
                    Timber.d(it)
                })
        compositeDisposable.add(fetchMoviesDisposable!!)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}