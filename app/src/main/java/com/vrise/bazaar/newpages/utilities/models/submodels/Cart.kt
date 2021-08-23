package com.vrise.bazaar.newpages.utilities.models.submodels


import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.newpages.utilities.models.newcart.ShopDeliveryItem


@JsonObject
data class Cart(

    @field:SerializedName("total_cartvalue")
    @field:JsonField(name = arrayOf("total_cartvalue"))
    val totalCartvalue: String = "0",

    @field:SerializedName("wallet_balance")
    @field:JsonField(name = arrayOf("wallet_balance"))
    val walletBalance: String = "0",

    /*@field:SerializedName("category_delivery")
    @field:JsonField(name = arrayOf("category_delivery"))
    val categoryDelivery: List<CategoryDeliveryItem?>? = null,*/

    /*@field:SerializedName("spot_delivery")
    @field:JsonField(name = arrayOf("spot_delivery"))
    val spotDelivery: SpotDelivery? = null,*/

    @field:SerializedName("shop_delivery")
    @field:JsonField(name = arrayOf("shop_delivery"))
    val shopDelivery: List<ShopDeliveryItem?>? = null,

    @field:SerializedName("default_address_id")
    @field:JsonField(name = arrayOf("default_address_id"))
    val default_address_id: String = ""
)