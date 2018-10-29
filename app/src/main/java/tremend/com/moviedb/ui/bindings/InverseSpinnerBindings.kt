package tremend.com.moviedb.ui.bindings

import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions.getSpinnerValue
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions.setSpinnerInverseBindingListener
import tremend.com.moviedb.utilities.extensions.SpinnerExtensions.setSpinnerValue


class InverseSpinnerBindings {

    @BindingAdapter("selectedValue")
    fun AppCompatSpinner.setSelectedValue(selectedValue: Any?) {
        setSpinnerValue(selectedValue)
    }

    @BindingAdapter("selectedValueAttrChanged")
    fun AppCompatSpinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener?) {
        setSpinnerInverseBindingListener(inverseBindingListener)
    }

    companion object InverseSpinnerBindings {

        @JvmStatic
        @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
        fun AppCompatSpinner.getSelectedValue(): Any? {
            return getSpinnerValue()
        }
    }
}