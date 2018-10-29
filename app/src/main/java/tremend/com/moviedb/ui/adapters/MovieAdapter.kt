package tremend.com.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import tremend.com.moviedb.R
import tremend.com.moviedb.data.vo.Movie
import tremend.com.moviedb.databinding.ItemMovieRowBinding

class MovieAdapter(
        private val callback: ((Movie) -> Unit)?
) : DataBoundListAdapter<Movie, ItemMovieRowBinding>(
        diffCallback = Movie.DIFF_CALLBACK
) {
    override fun createBinding(parent: ViewGroup): ItemMovieRowBinding {
        val binding = DataBindingUtil.inflate<ItemMovieRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_row,
                parent,
                false
        )
        binding.bAddReview.setOnClickListener { _ ->
            binding.item?.let { callback?.invoke(it) }
        }
        return binding
    }

    override fun bind(binding: ItemMovieRowBinding, item: Movie) {
        binding.item = item
    }
}