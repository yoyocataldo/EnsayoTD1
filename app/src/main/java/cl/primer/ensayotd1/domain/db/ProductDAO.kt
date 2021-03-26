package cl.primer.ensayotd1.domain.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(product: List<productEntity>)
    @Query("SELECT *FROM products")
    fun getProducts(): LiveData<List<productEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: productDetailEntity)
    @Query("SELECT * FROM productsDetail WHERE id = :pid")
    fun getDetail(pid:Int): LiveData<productDetailEntity>
}