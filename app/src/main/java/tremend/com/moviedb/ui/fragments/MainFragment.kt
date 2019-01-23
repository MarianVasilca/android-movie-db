package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import tremend.com.moviedb.R
import tremend.com.moviedb.databinding.FragmentMainBinding
import tremend.com.moviedb.ui.adapters.MovieAdapter
import tremend.com.moviedb.utilities.extensions.nav
import tremend.com.moviedb.viewmodels.MovieViewModel

/**
 * A fragment representing the start destination of the application.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private var adapter = MovieAdapter { nav(MainFragmentDirections.actionMainToReview(it)) }

    /**
     * Lazy delegate property to inject a ViewModel into a property
     */
    private val viewModel: MovieViewModel by viewModel()
    override fun getLayoutResource(): Int = R.layout.fragment_main
    override fun onBoundViews(savedInstanceState: Bundle?) {
        rvMovies.adapter = adapter
        viewModel.filteredMovies.observe(this, Observer { adapter.submitList(it) })
        getViewDataBinding().movieViewModel = viewModel
    }
}