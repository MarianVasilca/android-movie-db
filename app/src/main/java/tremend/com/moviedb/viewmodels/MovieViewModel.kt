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
import tremend.com.moviedb.data.vo.Status
import tremend.com.moviedb.utilities.ALL_GENRE_ID
import tremend.com.moviedb.utilities.ALL_GENRE_NAME
import tremend.com.moviedb.utilities.DEFAULT_VOTE_VALUE
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import java.util.*


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
        value = MovieFilter(null, Genre(ALL_GENRE_ID, ALL_GENRE_NAME), DEFAULT_VOTE_VALUE, null)
    }
    val selectedGenre = MutableLiveData<Genre>()
    val searchedTitle = MutableLiveData<String>()
    val searchedVote = MutableLiveData<Float>()
    val wasReleasedThisYear = MutableLiveData<Boolean>()

    val filteredMovies: LiveData<List<Movie>> = Transformations.switchMap(filter) { filter ->
        filter?.let {
            return@switchMap repository.searchMovies(it)
        }
    }
    val isFilterActive = Transformations.map(filter) { filter ->
        return@map filter != null && (!filter.title.isNullOrBlank() || filter.genre?.id != ALL_GENRE_ID ||
                !filter.year.isNullOrBlank() || filter.vote != DEFAULT_VOTE_VALUE)
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
        filter.addSource(searchedVote) { vote ->
            filter.value?.vote = vote * 2f
            filter.value = filter.value
        }
        filter.addSource(wasReleasedThisYear) { releasedThisYear ->
            filter.value?.year = if (releasedThisYear == true) Calendar.getInstance().get(Calendar.YEAR).toString() else ""
            filter.value = filter.value
        }
    }

    fun fetchMovies() {
        fetchMoviesDisposable?.dispose()
        fetchMoviesDisposable = repository.fetchMovies()
                .doOnSubscribe { setStatus(Status.LOADING) }
                .subscribe({ setStatus(Status.SUCCESS) }, { setErrorThrowable(it) })
        compositeDisposable.add(fetchMoviesDisposable!!)
    }

    override fun retryRequest() {
        fetchMovies()
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
        searchedVote.value = 0f
        wasReleasedThisYear.value = null
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun toggleFiltersVisibility() {
        visibleFilters.value = !visibleFilters.value!!
    }
}