package top.yisen614.databinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import top.yisen614.databinding.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    lateinit var product: Product
    lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)
        product = Product("yisen614", "It's description here")
        binding.product = product
    }

    fun showProduct(view: View) {
        Log.i("ProductActivity", product.toString())
        binding.notifyChange()
        binding.notifyPropertyChanged(BR.des)
    }
}
