package tremend.com.moviedb.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LongListConverter {

    @TypeConverter
    fun toLongList(value: String?): List<Long> {
        if (value.isNullOrEmpty()) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson<List<Long>>(value, listType)
    }

    @TypeConverter
    fun fromLongList(list: List<Long>?): String {
        if (list == null) {
            return ""
        }
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().toJson(list, listType)
    }

}
