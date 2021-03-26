package cl.primer.ensayotd1.domain.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun list2String(list: List<String>): String {
        return list.joinToString()

    }
    fun stringToList(value: String): List<String> {
return value.split(",").map {
    it.trim()
}
    }
}