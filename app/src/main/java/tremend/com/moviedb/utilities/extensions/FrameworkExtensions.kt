package tremend.com.moviedb.utilities.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.navigation.findNavController

// Navigation
fun View.nav(@IdRes resId: Int) = findNavController().navigate(resId)

fun View.navUp() = findNavController().navigateUp()