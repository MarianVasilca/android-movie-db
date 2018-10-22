package tremend.com.moviedb.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import timber.log.Timber
import tremend.com.moviedb.utilities.AutoClearedValue

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    @get:LayoutRes
    internal abstract val layoutResource: Int

    private lateinit var viewDataBinding: T
    @VisibleForTesting
    var binding: AutoClearedValue<T>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Timber.i("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView")
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        binding = AutoClearedValue(this)
        return viewDataBinding.root
    }

    /**
     * Using Kotlin Extensions we can achieve View Binding after view is created. Make sure that
     * every view method call is done after this method is called.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated")
        onBoundViews(savedInstanceState)
    }

    internal abstract fun onBoundViews(savedInstanceState: Bundle?)
}