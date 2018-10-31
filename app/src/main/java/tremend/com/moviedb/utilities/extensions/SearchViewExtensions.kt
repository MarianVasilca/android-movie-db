package tremend.com.moviedb.utilities.extensions

import androidx.appcompat.widget.SearchView
import androidx.databinding.InverseBindingListener


/**
 * set search viw on query text change listener
 */
fun SearchView.setSearchViewInverseBindingListener(listener: InverseBindingListener?) {
    if (listener == null) {
        setOnQueryTextListener(null)
    } else {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (tag != newText) {
                    listener.onChange()
                }
                return true
            }
        })

    }
}