package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.utilities.schedulers.MainScheduler

class MovieViewModel(
        app: Application,
        private val repository: MovieRepository,
        private val mainScheduler: MainScheduler
) : BaseAndroidViewModel(app, mainScheduler) {

    private val compositeDisposable = CompositeDisposable()
    private var fetchMoviesDisposable: Disposable? = null

    fun loadMovies(): LiveData<List<Movie>> {
        return repository.loadMovies()
    }

    fun fetchMovies() {
        fetchMoviesDisposable?.dispose()
        fetchMoviesDisposable = repository.fetchMovies()
                .doOnSubscribe { setLoading(true) }
                .subscribe({ setLoading(false) }, { setErrorThrowable(it) })
        compositeDisposable.add(fetchMoviesDisposable!!)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}