package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.data.vo.Genre
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.data.vo.MovieFilter
import tremend.com.moviedb.utilities.ALL_GENRE_ID
import tremend.com.moviedb.utilities.ALL_GENRE_NAME
import tremend.com.moviedb.utilities.schedulers.MainScheduler

class MovieViewModel(
        app: Application,
        private val repository: MovieRepository,
        private val mainScheduler: MainScheduler
) : BaseAndroidViewModel(app, mainScheduler) {

    private val compositeDisposable = CompositeDisposable()
    private var fetchMoviesDisposable: Disposable? = null
    private var fetchGenresDisposable: Disposable? = null

    val visibleFilters = MutableLiveData<Boolean>().apply { value = false }
    val genres = repository.loadGenres()

    private val filter = MediatorLiveData<MovieFilter>().apply {
        value = MovieFilter(null, Genre(ALL_GENRE_ID, ALL_GENRE_NAME))
    }
    val selectedGenre = MutableLiveData<Genre>()
    val searchedTitle = MutableLiveData<String>()

    val filteredMovies: LiveData<List<Movie>> = Transformations.switchMap(filter) { filter ->
        filter?.let {
            return@switchMap repository.searchMovies(it)
        }
    }
    val isFilterActive = Transformations.map(filter) { filter ->
        return@map (filter != null) && (!filter.title.isNullOrBlank() || filter.genre?.id != ALL_GENRE_ID)
    }!!

    init {
        filter.addSource(selectedGenre) { genre ->
            filter.value?.genre = genre
            filter.value = filter.value
        }
        filter.addSource(searchedTitle) { title ->
            filter.value?.title = title
            filter.value = filter.value
        }
    }

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

    fun clearFilters() {
        searchedTitle.value = ""
        selectedGenre.value = Genre(ALL_GENRE_ID, ALL_GENRE_NAME)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun toggleFiltersVisibility() {
        visibleFilters.value = !visibleFilters.value!!
    }
}