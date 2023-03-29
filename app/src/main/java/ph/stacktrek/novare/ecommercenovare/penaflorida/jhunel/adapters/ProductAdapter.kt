package ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.databinding.ProductItemBinding
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.model.Product

class ProductAdapter(private val context:Context,
                     private var productList:ArrayList<Product>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    fun deleteProduct(position:Int){
        productList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val productItemBinding = ProductItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(productItemBinding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bindItems(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val productItemBinding: ProductItemBinding):
        RecyclerView.ViewHolder(productItemBinding.root){

        fun bindItems(product: Product){
            productItemBinding.productName.text = product.name
        }
    }
}
