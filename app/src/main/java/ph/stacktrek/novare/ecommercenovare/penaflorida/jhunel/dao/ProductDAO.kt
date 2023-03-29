package ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.dao

import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.model.Product


interface ProductDAO {
    fun addProduct(product: Product)
    fun getProducts(): ArrayList<Product>
    fun updateProduct(product: Product)
    fun deleteProduct(product: Product)
}

class ProductDAOStubImplementation: ProductDAO{
    private var productList: ArrayList<Product> = ArrayList()

    init{
        productList.add(Product("Corned Beef"))
        productList.add(Product("Canned Tuna"))
        productList.add(Product("Canned Carrots and Corn"))
        productList.add(Product("Powdered Milk"))
        productList.add(Product("Coffee"))
        productList.add(Product("Canned Beer"))
        productList.add(Product("Canned Soda"))
        productList.add(Product("Creamer"))
        productList.add(Product("Bottled Water"))
        productList.add(Product("Bottled Soda"))
        productList.add(Product("Bottled Tea"))
        productList.add(Product("Bottled Milk Tea"))
    }

    override fun addProduct(product: Product) {
        productList.add(product)
    }

    override fun getProducts(): ArrayList<Product> = productList

    //TODO("Not yet implemented")
    override fun updateProduct(product: Product) {

    }

    //TODO("Not yet implemented")
    override fun deleteProduct(product: Product) {

    }

}
