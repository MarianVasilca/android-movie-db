package tremend.com.moviedb.data.vo

import com.google.gson.annotations.SerializedName

data class MoviesResponse(val page: Int,
                          @SerializedName("total_results") val totalResult: Int,
                          @SerializedName("total_pages") val totalPage: Int,
                          val results: List<Movie>)