package cl.primer.ensayotd1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import cl.primer.ensayotd1.data.model.Product
import cl.primer.ensayotd1.databinding.ProductItemBinding
import coil.load

class ProductAdapter: RecyclerView.Adapter<ProductVH>() {

    private var productList = listOf<Product>()
    private val selectedItem = MutableLiveData<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
      val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProductVH(binding)
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            selectedItem.value = product
        }

    }

    override fun getItemCount(): Int {
       return productList.size

    }

    fun selectedItem(): LiveData<Product>{
        return selectedItem}

        fun update(mProductList: List<Product>){
            productList = mProductList
            notifyDataSetChanged()
        }
    }


class ProductVH(val binding:ProductItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(product: Product){
        binding.tvProductName.text = product.name
        binding.priceProduct.text = product.price.toString()
        binding.imgProduct.load(product.image)
    }
}