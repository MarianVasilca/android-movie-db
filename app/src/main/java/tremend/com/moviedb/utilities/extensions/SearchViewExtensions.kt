package tremend.com.moviedb.utilities.extensions

import androidx.appcompat.widget.SearchView
import androidx.databinding.InverseBindingListener


object SearchViewExtensions {
    /**
     * set spinner onItemSelectedListener listener
     */
    fun SearchView.setSearchViewInverseBindingListener(listener: InverseBindingListener?) {
        if (listener == null) {
            setOnQueryTextListener(null)
        } else {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (tag != query) {
                        listener.onChange()
                    }
                    return true
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

    interface QueryListener {
        fun onQueryChanged(value: String)
    }
}