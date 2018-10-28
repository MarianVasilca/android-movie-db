package tremend.com.moviedb.ui.bindings

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import tremend.com.moviedb.utilities.extensions.SearchViewExtensions.setSearchViewInverseBindingListener


class InverseSearchViewBindings {

    @BindingAdapter("query")
    fun SearchView.setQuery(searchQuery: String?) {
        setQuery(searchQuery, false)
    }

    @BindingAdapter("queryAttrChanged")
    fun SearchView.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSearchViewInverseBindingListener(inverseBindingListener)
    }

    companion object InverseSearchViewBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "query", event = "queryAttrChanged")
        fun SearchView.getQuery(): String? {
            return query.toString()
        }
    }
}