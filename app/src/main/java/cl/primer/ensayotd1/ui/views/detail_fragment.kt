package cl.primer.ensayotd1.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.primer.ensayotd1.databinding.FragmentDetailFragmentBinding
import cl.primer.ensayotd1.ui.viewmodel.ProductVM
import coil.load


class detail_fragment : Fragment() {
    private lateinit var binding: FragmentDetailFragmentBinding
    private val productVM: ProductVM by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailFragmentBinding.inflate(inflater)
        val product = productVM.getSelected()
        productVM.consumeDetail(product.id)
        productVM.getDetail(product.id).observe(viewLifecycleOwner, {
            it?.let {
                binding.imgProducDetail.load(it.image)
                binding.productNameDetail.text = it.name
                binding.tvPriceProductDetail.text = it.price.toString()
                binding.txtProductDetail.text = it.description
            }
        })

        return binding.root
    }


}