package tremend.com.moviedb.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import tremend.com.moviedb.utilities.AutoClearedValue

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    internal abstract val layoutResource: Int

    private lateinit var viewDataBinding: T
    @VisibleForTesting
    var binding: AutoClearedValue<T>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding = AutoClearedValue(this)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBoundViews(savedInstanceState)
    }

    internal abstract fun onBoundViews(savedInstanceState: Bundle?)
}