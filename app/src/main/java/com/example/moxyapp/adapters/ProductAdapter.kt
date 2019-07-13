package com.example.moxyapp.adapters

import android.app.PendingIntent.getActivity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.moxyapp.R
import com.example.moxyapp.models.FullProduct
import java.util.ArrayList

class ProductAdapter(baseContext: Context) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    internal var context: Context
    internal var list: MutableList<FullProduct>


    init {
        this.context = baseContext
        this.list = ArrayList<FullProduct>() as MutableList<FullProduct>
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val productViewHolder = p0 as ViewHolder
        productViewHolder.name.text = list?.get(p1)?.name
        productViewHolder.brand.text = list?.get(p1)?.brand.name

        if (!list.get(p1).images.isEmpty()) {
            Glide.with(context).load(list.get(p1).images.get(0).file!!)
                .into(productViewHolder.image)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductAdapter.ViewHolder {

        val view = LayoutInflater.from(p0.context).inflate(R.layout.product_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    fun addItem(product: FullProduct) {
        list?.add(product)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.item_name)
        var brand: TextView = itemView.findViewById(R.id.general_info)
        var image: ImageView = itemView.findViewById(R.id.item_image)


    }

}