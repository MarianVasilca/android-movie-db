package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import timber.log.Timber
import tremend.com.moviedb.R
import tremend.com.moviedb.utilities.schedulers.MainScheduler

open class BaseAndroidViewModel(
        val app: Application,
        private val mainScheduler: MainScheduler
) : AndroidViewModel(app) {

    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    protected fun setErrorThrowable(throwable: Throwable) {
        setLoading(false)
        val errorResource = when (throwable) {
            is HttpException -> R.string.error_http
            else -> R.string.error_general
        }
        errorMessage.postValue(app.getString(errorResource))
    }

    fun setLoading(isLoading: Boolean) {
        Timber.d("Set loading $isLoading")
        mainScheduler.runOnThread {
            this.isLoading.value = isLoading
        }
    }
}