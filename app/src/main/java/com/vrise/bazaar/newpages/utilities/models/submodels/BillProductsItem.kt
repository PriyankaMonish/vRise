package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import java.io.Serializable

@JsonObject
data class BillProductsItem(

        @field:SerializedName("total_amount")
        @field:JsonField(name = arrayOf("total_amount"))
        var totalAmount: String? = null,

        @field:SerializedName("product_id")
        @field:JsonField(name = arrayOf("product_id"))
        val productId: String? = null,

        @field:SerializedName("round_total")
        @field:JsonField(name = arrayOf("round_total"))
        val roundTotal: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("product_price")
        @field:JsonField(name = arrayOf("product_price"))
        val productPrice: String? = null,

        @field:SerializedName("type")
        @field:JsonField(name = arrayOf("type"))
        val type: String? = null,

        @field:SerializedName("product_name")
        @field:JsonField(name = arrayOf("product_name"))
        val productName: String? = null,

        @field:SerializedName("product_quantity")
        @field:JsonField(name = arrayOf("product_quantity"))
        val productQuantity: String? = null
) : Serializable