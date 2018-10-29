package tremend.com.moviedb.utilities.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

fun Fragment.nav(directions: NavDirections) {
    NavHostFragment.findNavController(this).navigate(directions)
}