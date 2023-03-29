package ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.adapters.ProductAdapter
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.adapters.SwipeCallback
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.dao.ProductDAO
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.dao.ProductDAOStubImplementation
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.databinding.ActivityLoginBinding
import ph.stacktrek.novare.ecommercenovare.penaflorida.jhunel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var productAdapter:ProductAdapter
    private lateinit var productDAO: ProductDAO
    private lateinit var itemTouchHelper: ItemTouchHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        Log.d("MAIN ACTIVITY","USERNAME BUNDLE: ${bundle!!.getString("bundle_username")}")

        val extra = intent.getStringExtra("username")
        Log.d("MAIN ACTIVITY","USERNAME EXTRA : $extra")

        loadProducts()

    }

    fun loadProducts(){
        productDAO = ProductDAOStubImplementation()
        productAdapter = ProductAdapter(applicationContext,
        productDAO.getProducts())
        with(binding.productsList){
            layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL,
                false)
//            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = productAdapter
        }
        var swipeCallback = SwipeCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        swipeCallback.productAdapter = productAdapter
        itemTouchHelper = ItemTouchHelper(swipeCallback).apply {
            attachToRecyclerView(binding.productsList)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val goToMain = Intent(applicationContext,
            LoginActivity::class.java)

        startActivity(goToMain)
        finish()

    }
}