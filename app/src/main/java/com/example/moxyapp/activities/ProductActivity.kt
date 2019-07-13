package com.example.moxyapp.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.moxyapp.R
import com.example.moxyapp.adapters.ProductAdapter
import com.example.moxyapp.interfaces.ServiceInterface
import com.example.moxyapp.models.ProductsList
import com.example.moxyapp.presenters.ProductPresenter
import com.example.moxyapp.views.ProductView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductActivity : MvpAppCompatActivity(), ProductView {


    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var productPresenter: ProductPresenter
    lateinit var retrofit: Retrofit
    lateinit var service: ServiceInterface
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        productPresenter.creatingRetrofitAndAdapter()
        productPresenter.loadingList()


    }

    override fun retrofitCreate() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://apipost.ritm.uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun setAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.product_list)
        val layoutManager1 = LinearLayoutManager(baseContext)
        recyclerView?.layoutManager = layoutManager1
        adapter = ProductAdapter(application)
        recyclerView?.adapter = adapter
    }

    override fun loadList() {
        service = retrofit.create(ServiceInterface::class.java)
        val call = service.getProducts()
        call.enqueue(object : Callback<ProductsList> {
            override fun onResponse(call: Call<ProductsList>, response: Response<ProductsList>) {
                productPresenter.onResponse(response)
            }

            override fun onFailure(call: Call<ProductsList>, t: Throwable) {
                productPresenter.onFailure(t)
            }


        })
    }

    override fun forEach(response: Response<ProductsList>) {
        for (product in response.body()!!.list!!) {
            adapter.addItem(product)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }

    override fun showError(message: Int) {
        Toast.makeText(applicationContext, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        Toast.makeText(applicationContext, getString(R.string.loaded), Toast.LENGTH_SHORT).show()

    }

}
