package tremend.com.moviedb.ui.adapters

import tremend.com.moviedb.R
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.databinding.ItemMovieRowBinding

class MovieAdapter(
        private val callback: ((Movie) -> Unit)?
) : DataBoundListAdapter<Movie, ItemMovieRowBinding>(
        diffCallback = Movie.DIFF_CALLBACK
) {
    override fun getItemLayout(): Int = R.layout.item_movie_row
    override fun addClickListeners(binding: ItemMovieRowBinding) {
        binding.bAddReview.setOnClickListener { _ ->
            binding.item?.let { callback?.invoke(it) }
        }
    }

    override fun bind(binding: ItemMovieRowBinding, item: Movie) {
        binding.item = item
    }
}