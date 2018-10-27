package tremend.com.moviedb.ui.bindings

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun imageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(imageUrl)
                .into(view)
    }
}

@BindingAdapter("goneIf")
fun goneIf(view: View, isGone: Boolean?) {
    view.visibility = if (isGone == true) View.VISIBLE else View.GONE
}