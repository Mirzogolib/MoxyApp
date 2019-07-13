package com.example.moxyapp.models

import com.google.gson.annotations.SerializedName

class ProductsList {
    @SerializedName("results")
    var list: List<FullProduct>? = null


}