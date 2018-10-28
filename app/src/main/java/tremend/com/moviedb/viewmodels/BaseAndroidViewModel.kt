package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import tremend.com.moviedb.R

open class BaseAndroidViewModel(val app: Application) : AndroidViewModel(app) {

    private val errorMessage = MutableLiveData<String>()

    fun getErrorMessageLiveData(): MutableLiveData<String> {
        return errorMessage
    }

    protected fun setErrorThrowable(throwable: Throwable) {
        val errorResource = when (throwable) {
            is HttpException -> R.string.error_http
            else -> R.string.error_general
        }
        errorMessage.value = app.getString(errorResource)
    }
}