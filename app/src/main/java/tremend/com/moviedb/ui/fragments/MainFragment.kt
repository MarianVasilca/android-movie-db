package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import tremend.com.moviedb.R
import tremend.com.moviedb.databinding.FragmentMainBinding

/**
 * A fragment representing the start destination of the application.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_main

    override fun onBoundViews(savedInstanceState: Bundle?) {
    }

}