package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import timber.log.Timber
import tremend.com.moviedb.R
import tremend.com.moviedb.data.vo.Status
import tremend.com.moviedb.utilities.schedulers.MainScheduler
import java.net.UnknownHostException

abstract class BaseAndroidViewModel(
        val app: Application,
        private val mainScheduler: MainScheduler
) : AndroidViewModel(app) {

    val errorMessage = MutableLiveData<String>()
    val status = MutableLiveData<Status>()

    protected fun setErrorThrowable(throwable: Throwable) {
        setStatus(Status.ERROR)
        val errorResource = when (throwable) {
            is HttpException -> R.string.error_http
            is UnknownHostException -> R.string.error_no_internet
            else -> R.string.error_general
        }
        errorMessage.postValue(app.getString(errorResource))
    }

    fun setStatus(status: Status) {
        Timber.d("Set status $status")
        mainScheduler.runOnThread {
            this.status.value = status
        }
    }

    abstract fun retryRequest()
}