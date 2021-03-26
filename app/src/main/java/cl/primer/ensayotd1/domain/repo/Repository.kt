package cl.primer.ensayotd1.domain.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cl.primer.ensayotd1.data.model.Product
import cl.primer.ensayotd1.data.model.productDetail
import cl.primer.ensayotd1.data.remote.RetrofitClient
import cl.primer.ensayotd1.domain.db.ProductApplication
import cl.primer.ensayotd1.domain.db.productDetailEntity
import cl.primer.ensayotd1.domain.db.productEntity

class Repository {

    private val productDB = ProductApplication.db!!.productDao()

    fun productList(): LiveData<List<Product>> = Transformations.map(productDB.getProducts()) {
        it.map { entity ->
            db2api(entity)
        }
    }

    fun getDetailFromDB(pid: Int): LiveData<productDetail> = Transformations.map(productDB.getDetail(pid)) {
        it?.let { detailEntity ->
            db2api(detailEntity)
        }
    }

    suspend fun getProducts() {
        val response = RetrofitClient
            .RetrofitInstance()
            .getProducts()

        response.let {
            when(it.isSuccessful) {
                true -> {
                    response.body()?.let { productList ->
                        val map = productList.map { product ->
                            api2db(product)
                        }
                        productDB.insertProducts(map)
                    }
                }
                false -> {
                    Log.d("Repo", "error en Repo")
                }
            }
        }
    }

    suspend fun getDetail(id: Int) {
        val response = RetrofitClient.RetrofitInstance().getProduct(id)

        if (response.isSuccessful) {
            response.body()?.let { detail ->
                productDB.insertDetail(api2db(detail))
            }
        } else {
            Log.d("Repo", "error en el detalle ${response.code()}")
        }
    }
}

fun api2db(product: Product): productEntity {
    return productEntity(product.id, product.name, product.price, product.image)
}

fun db2api(productEntity: productEntity): Product {
    return Product(productEntity.id, productEntity.name, productEntity.price, productEntity.image)
}

fun api2db(detail: productDetail): productDetailEntity {
    return productDetailEntity(detail.id, detail.name, detail.price, detail.image, detail.description, detail.lastPrice, detail.credit)
}

fun db2api(detailEntity: productDetailEntity): productDetail {
    return productDetail(detailEntity.id, detailEntity.name, detailEntity.price, detailEntity.image, detailEntity.description, detailEntity.lastPrice, detailEntity.credit)
}
