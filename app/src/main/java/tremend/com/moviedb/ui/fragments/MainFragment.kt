package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import tremend.com.moviedb.R
import tremend.com.moviedb.databinding.FragmentMainBinding
import tremend.com.moviedb.ui.adapters.MovieAdapter

/**
 * A fragment representing the start destination of the application.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private var adapter: MovieAdapter? = null

    override val layoutResource: Int
        get() = R.layout.fragment_main

    override fun onBoundViews(savedInstanceState: Bundle?) {
        setupMovieAdapter()
    }

    private fun setupMovieAdapter() {
        val layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        rvMovies.layoutManager = layoutManager
        adapter = MovieAdapter {
            //TODO add arg movie
            NavHostFragment.findNavController(this).navigate(R.id.action_main_fragment_to_review_fragment)
        }
        rvMovies.adapter = adapter
    }
}