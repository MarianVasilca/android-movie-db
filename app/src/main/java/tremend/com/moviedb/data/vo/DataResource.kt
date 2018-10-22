package tremend.com.moviedb.data.vo

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class DataResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): DataResource<T> {
            return DataResource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): DataResource<T> {
            return DataResource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): DataResource<T> {
            return DataResource(Status.LOADING, data, null)
        }
    }
}