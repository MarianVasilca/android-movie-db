package tremend.com.moviedb.ui.bindings

import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import tremend.com.moviedb.utilities.extensions.getSpinnerValue
import tremend.com.moviedb.utilities.extensions.setSpinnerInverseBindingListener
import tremend.com.moviedb.utilities.extensions.setSpinnerValue

@BindingAdapter("selectedValue")
fun setSelectedValue(view: AppCompatSpinner, selectedValue: Any?) {
    view.setSpinnerValue(selectedValue)
}

@BindingAdapter("selectedValueAttrChanged")
fun setInverseBindingListener(view: AppCompatSpinner, inverseBindingListener: InverseBindingListener?) {
    view.setSpinnerInverseBindingListener(inverseBindingListener)
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun getSelectedValue(view: AppCompatSpinner): Any? {
    return view.getSpinnerValue()
}