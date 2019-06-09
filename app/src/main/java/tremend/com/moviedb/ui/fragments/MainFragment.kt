package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import tremend.com.moviedb.R
import tremend.com.moviedb.data.db.AppDatabase
import tremend.com.moviedb.data.repositories.MovieRepository
import tremend.com.moviedb.databinding.FragmentMainBinding
import tremend.com.moviedb.ui.adapters.MovieAdapter
import tremend.com.moviedb.viewmodels.MovieViewModel

/**
 * A fragment representing the start destination of the application.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    //    private val adapter: MovieAdapter by inject()
    private val adapter = MovieAdapter()

    /**
     * Lazy delegate property to inject a ViewModel into a property
     */
//    private val viewModel: MovieViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_main

    override fun onBoundViews(savedInstanceState: Bundle?) {
//        bindScope(getOrCreateScope("sessionMovieAdapter"))
        val viewModel = activity?.let { MovieViewModel(it.application, MovieRepository(database = AppDatabase.getInstance(it))) }
        rvMovies.adapter = adapter
        viewModel?.filteredMovies?.observe(this, Observer { adapter.submitList(it) })
        getViewDataBinding().movieViewModel = viewModel
    }
}