package tremend.com.moviedb.data.api

import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import tremend.com.moviedb.BuildConfig
import tremend.com.moviedb.data.vo.GenresResponse
import tremend.com.moviedb.data.vo.MoviesResponse

interface ApiService {

    @GET("discover/movie")
    fun getMovies(@Query("sort_by") sortBy: String): Single<MoviesResponse>

    @GET("genre/movie/list")
    fun getGenres(): Call<GenresResponse>

    companion object {
        fun create(): ApiService {
            val tmdbApiKeyInterceptor = Interceptor { chain ->
                var request: Request = chain.request()
                val url = request
                        .url()
                        .newBuilder()
                        .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                        .build()

                request = request
                        .newBuilder()
                        .url(url)
                        .build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                    .addInterceptor(tmdbApiKeyInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.TMDB_BASE_URL)
                    .client(client)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}