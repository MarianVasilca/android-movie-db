package tremend.com.moviedb.ui.adapters

import androidx.navigation.findNavController
import tremend.com.moviedb.R
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.databinding.ItemMovieRowBinding
import tremend.com.moviedb.ui.fragments.MainFragmentDirections

class MovieAdapter : DataBoundListAdapter<Movie, ItemMovieRowBinding>(
        diffCallback = Movie.DIFF_CALLBACK
) {
    override fun getItemLayout(): Int = R.layout.item_movie_row
    override fun addClickListeners(binding: ItemMovieRowBinding) {
        binding.bAddReview.setOnClickListener { view ->
            binding.item?.let { view.findNavController().navigate(MainFragmentDirections.actionMainToReview(it)) }
        }
    }

    override fun bind(binding: ItemMovieRowBinding, item: Movie) {
        binding.item = item
    }
}