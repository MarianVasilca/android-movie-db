package tremend.com.moviedb.ui.bindings

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import tremend.com.moviedb.utilities.extensions.setSearchViewInverseBindingListener

@BindingAdapter("query")
fun setQuery(view: SearchView, searchQuery: String?) {
    view.setQuery(searchQuery, false)
}

@BindingAdapter("queryAttrChanged")
fun setInverseBindingListener(view: SearchView, inverseBindingListener: InverseBindingListener?) {
    view.setSearchViewInverseBindingListener(inverseBindingListener)
}

@InverseBindingAdapter(attribute = "query", event = "queryAttrChanged")
fun getQuery(view: SearchView): String? {
    return view.query.toString()
}