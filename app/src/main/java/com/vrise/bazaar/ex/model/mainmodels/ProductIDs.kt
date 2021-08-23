package com.vrise.bazaar.ex.model.mainmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
class ProductIDs(
    @field:SerializedName("seller_product_id")
    @field:JsonField(name = arrayOf("seller_product_id"))
    var seller_product_id: String? =  null,

    @field:SerializedName("product_size_id")
    @field:JsonField(name = arrayOf("product_size_id"))
    var product_size_id: String? =  null,

    @field:SerializedName("quantity")
    @field:JsonField(name = arrayOf("quantity"))
    var quantity: String? =  null,


) : Serializable

