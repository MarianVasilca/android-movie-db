package tremend.com.moviedb.ui.bindings

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: String?, placeHolder: Drawable) {
    if (imageUrl == null) {
        imageView.setImageDrawable(placeHolder)
    } else {
        val requestOptions = RequestOptions()
                .placeholder(placeHolder)
                .error(placeHolder)
        Glide.with(imageView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageUrl)
                .into(imageView)
    }
}

@BindingAdapter("visibleIf")
fun visibleIf(view: View, visibleIf: Boolean?) {
    view.visibility = if (visibleIf == true) View.VISIBLE else View.GONE
}
