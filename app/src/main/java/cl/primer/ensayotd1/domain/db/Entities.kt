package cl.primer.ensayotd1.domain.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class productEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Long,
    val image: String

)
@Entity(tableName = "productsDetail")
data class productDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Long,
    val image: String,
    val description: String,
    val lastPrice: Long,
    val credit: Boolean
)
