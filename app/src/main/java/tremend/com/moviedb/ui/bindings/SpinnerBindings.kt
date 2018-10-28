package tremend.com.moviedb.ui.bindings

import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions.setSpinnerEntries
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions.setSpinnerItemSelectedListener
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions.setSpinnerValue

class SpinnerBindings {

    @BindingAdapter("entries")
    fun AppCompatSpinner.setEntries(entries: List<Any>?) {
        setSpinnerEntries(entries)
    }

    @BindingAdapter("onItemSelected")
    fun AppCompatSpinner.setOnItemSelectedListener(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
        setSpinnerItemSelectedListener(itemSelectedListener)
    }

    @BindingAdapter("newValue")
    fun AppCompatSpinner.setNewValue(newValue: Any?) {
        setSpinnerValue(newValue)
    }
}