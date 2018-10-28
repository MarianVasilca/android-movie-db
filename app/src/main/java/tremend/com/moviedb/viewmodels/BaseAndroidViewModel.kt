package tremend.com.moviedb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import tremend.com.moviedb.R

open class BaseAndroidViewModel(val app: Application) : AndroidViewModel(app) {

    private val errorMessage = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Boolean>()

    fun getErrorMessageLiveData(): MutableLiveData<String> {
        return errorMessage
    }

    protected fun setErrorThrowable(throwable: Throwable) {
        setLoading(false)
        val errorResource = when (throwable) {
            is HttpException -> R.string.error_http
            else -> R.string.error_general
        }
        errorMessage.postValue(app.getString(errorResource))
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading.postValue(isLoading)
    }
}