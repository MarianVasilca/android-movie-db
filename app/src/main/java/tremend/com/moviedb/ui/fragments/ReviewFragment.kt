package tremend.com.moviedb.ui.fragments


import android.os.Bundle
import tremend.com.moviedb.R
import tremend.com.moviedb.databinding.FragmentReviewBinding

class ReviewFragment : BaseFragment<FragmentReviewBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_review

    override fun onBoundViews(savedInstanceState: Bundle?) {
    }

}
