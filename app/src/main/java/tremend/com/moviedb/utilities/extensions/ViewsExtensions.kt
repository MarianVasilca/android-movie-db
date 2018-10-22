package tremend.com.moviedb.utilities.extensions

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbarLong(@StringRes message: Int) =
        showSnackbarLong(resources.getString(message))

fun View.showSnackbarLong(message: String) =
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()

fun View.setVisibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}