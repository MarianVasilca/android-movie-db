package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import tremend.com.moviedb.R
import tremend.com.moviedb.databinding.FragmentMainBinding
import tremend.com.moviedb.ui.adapters.MovieAdapter
import tremend.com.moviedb.viewmodels.MovieViewModel
import javax.inject.Inject

/**
 * A fragment representing the start destination of the application.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {
    @Inject
    lateinit var adapter: MovieAdapter
    @Inject
    lateinit var viewModel: MovieViewModel

    override fun getLayoutResource(): Int = R.layout.fragment_main
    override fun onBoundViews(savedInstanceState: Bundle?) {
        rvMovies.adapter = adapter
        viewModel.filteredMovies.observe(this, Observer { adapter.submitList(it) })
        getViewDataBinding().movieViewModel = viewModel
    }
}