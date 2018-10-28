package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.architecture.ext.getViewModelByClass
import tremend.com.moviedb.R
import tremend.com.moviedb.databinding.FragmentMainBinding
import tremend.com.moviedb.ui.adapters.MovieAdapter
import tremend.com.moviedb.viewmodels.MovieViewModel

/**
 * A fragment representing the start destination of the application.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private var adapter: MovieAdapter? = null

    private val viewModel: MovieViewModel by lazy { getViewModelByClass(clazz = MovieViewModel::class) }

    override val layoutResource: Int
        get() = R.layout.fragment_main

    override fun onBoundViews(savedInstanceState: Bundle?) {
        setupMovieAdapter()
        subscribeUI()
        // set the view model
        getViewDataBinding().movieViewModel = viewModel

        // fetch the movies
        viewModel.fetchMovies()
        viewModel.fetchGenres()
    }

    private fun subscribeUI() {
        viewModel.filteredMovies.observe(this, Observer { adapter?.submitList(it) })
    }

    private fun setupMovieAdapter() {
        if (adapter == null) {
            adapter = MovieAdapter {
                val action = MainFragmentDirections.actionMainFragmentToReviewFragment(it)
                NavHostFragment.findNavController(this).navigate(action)
            }
        }
        rvMovies.adapter = adapter
    }
}