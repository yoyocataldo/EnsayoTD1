package cl.primer.ensayotd1.domain.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [productEntity::class, productDetailEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDataBase: RoomDatabase(){
    abstract fun productDao():ProductDAO

}

class ProductApplication: Application (){
    companion object {
        var db: ProductDataBase? = null

    }

    override fun onCreate() {
        super.onCreate()
        db = Room.
        databaseBuilder(this, ProductDataBase::class.java, "product_db")
            .build()
    }

}