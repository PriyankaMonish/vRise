package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class Shoppss(

    @field:SerializedName("address")
    @field:JsonField(name = arrayOf("address"))
    val address: String? = null,

    @field:SerializedName("distance")
    @field:JsonField(name = arrayOf("distance"))
    val distance: String? = null,

    @field:SerializedName("address2")
    @field:JsonField(name = arrayOf("address2"))
    val address2: String? = null,

    @field:SerializedName("city")
    @field:JsonField(name = arrayOf("city"))
    val city: String? = null,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String? = null,

    @field:SerializedName("latitude")
    @field:JsonField(name = arrayOf("latitude"))
    val latitude: String? = null,

    @field:SerializedName("packing_time")
    @field:JsonField(name = arrayOf("packing_time"))
    val packingtime: String? = null,

    @field:SerializedName("rating")
    @field:JsonField(name = arrayOf("rating"))
    val rating: String? = null,

    @field:SerializedName("store_name")
    @field:JsonField(name = arrayOf("store_name"))
    val storeName: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("license")
    @field:JsonField(name = arrayOf("license"))
    val licenses: String? = null,

    @field:SerializedName("longitude")
    @field:JsonField(name = arrayOf("longitude"))
    val longitude: String? = null,

    @field:SerializedName("seller_pickup_offer")
    @field:JsonField(name = arrayOf("seller_pickup_offer"))
    val seller_pickup_offer: String? = null,

   @field:SerializedName("tagstatus")
    @field:JsonField(name = arrayOf("tagstatus"))
    val tagstatus: String? = null,

   @field:SerializedName("tagicon")
    @field:JsonField(name = arrayOf("tagicon"))
    val tagicon: String? = null

)
