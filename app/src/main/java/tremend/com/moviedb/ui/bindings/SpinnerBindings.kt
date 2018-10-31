package tremend.com.moviedb.ui.bindings

import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import tremend.com.moviedb.utilities.extensions.ItemSelectedListener
import tremend.com.moviedb.utilities.extensions.setSpinnerEntries
import tremend.com.moviedb.utilities.extensions.setSpinnerItemSelectedListener
import tremend.com.moviedb.utilities.extensions.setSpinnerValue

@BindingAdapter("entries")
fun setEntries(view: AppCompatSpinner, entries: List<Any>?) {
    view.setSpinnerEntries(entries)
}

@BindingAdapter("onItemSelected")
fun setOnItemSelectedListener(view: AppCompatSpinner, itemSelectedListener: ItemSelectedListener?) {
    view.setSpinnerItemSelectedListener(itemSelectedListener)
}

@BindingAdapter("newValue")
fun setNewValue(view: AppCompatSpinner, newValue: Any?) {
    view.setSpinnerValue(newValue)
}