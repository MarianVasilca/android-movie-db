package tremend.com.moviedb.ui.bindings

import androidx.databinding.DataBindingComponent

class BindingComponent : DataBindingComponent {
    override fun getInverseSearchViewBindings(): InverseSearchViewBindings {
        return InverseSearchViewBindings()
    }

    override fun getSpinnerBindings(): SpinnerBindings {
        return SpinnerBindings()
    }

    override fun getInverseSpinnerBindings(): InverseSpinnerBindings {
        return InverseSpinnerBindings()
    }

}