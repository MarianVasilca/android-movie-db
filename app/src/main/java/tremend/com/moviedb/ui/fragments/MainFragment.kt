package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        viewModel.fetchMovies()
        viewModel.fetchGenres()
    }

    private fun subscribeUI() {
        viewModel.loadMovies().observe(this, Observer { adapter?.submitList(it) })
//        viewModel.loadGenres().observe(this, Observer {
            //TODO submit list for spinner adapter
//            adapter?.submitList(it)
//        })

    }

    private fun setupMovieAdapter() {
        val layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        rvMovies.layoutManager = layoutManager
        adapter = MovieAdapter {
            val action = MainFragmentDirections.actionMainFragmentToReviewFragment(it)
            NavHostFragment
                    .findNavController(this)
                    .navigate(action)
        }
        rvMovies.adapter = adapter
    }
}