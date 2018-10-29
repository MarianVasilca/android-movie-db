package tremend.com.moviedb.utilities.extensions

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import kotlin.reflect.KClass

fun Fragment.nav(directions: NavDirections) {
    NavHostFragment.findNavController(this).navigate(directions)
}

fun <T : Activity> Activity.startAndFinish(clazz: KClass<T>) {
    startActivity(Intent(applicationContext, clazz.java))
    finish()
}