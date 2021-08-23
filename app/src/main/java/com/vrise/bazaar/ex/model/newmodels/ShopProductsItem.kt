package com.vrise.bazaar.ex.model.newmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class ShopProductsItem(

    @field:SerializedName("select")
    @field:JsonField(name = arrayOf("select"))
    var select: Boolean = false,

    @field:SerializedName("subcategory")
    @field:JsonField(name = arrayOf("subcategory"))
    val subcategory: Subcategory? = null,



    @field:SerializedName("items")
    @field:JsonField(name = arrayOf("items"))
    val items: List<ItemsItem?>? = null
)