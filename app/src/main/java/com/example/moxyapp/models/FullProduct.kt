package com.example.moxyapp.models
import com.google.gson.annotations.SerializedName

data class FullProduct(
    @SerializedName("brand")
    val brand: Brand,
    @SerializedName("capacity")
    val capacity: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("creator")
    val creator: Creator,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("kg_value")
    val kgValue: String,
    @SerializedName("measurement")
    val measurement: Measurement,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: Type,
    @SerializedName("vendor_code")
    val vendorCode: String
)

data class Measurement(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Type(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Creator(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)

data class Brand(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Image(
    @SerializedName("file")
    val file: String,
    @SerializedName("file_id")
    val fileId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_primary")
    val isPrimary: Boolean
)