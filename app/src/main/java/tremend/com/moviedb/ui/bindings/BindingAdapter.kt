package tremend.com.moviedb.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("imageFromUrl")
fun imageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(imageUrl)
                .into(view)
    }
}

@BindingAdapter("goneIf")
fun goneIf(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) view.hide() else view.show()
}