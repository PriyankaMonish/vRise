package com.vrise.bazaar.ex.model.temp


import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.DefaultAddress
import com.vrise.bazaar.ex.model.newcart.ShopDeliveryItem
import com.vrise.bazaar.ex.model.submodels.shippingmethods
import com.vrise.bazaar.ex.shop.pages.main.ShippingMethod


@JsonObject
data class Cart(

	@field:SerializedName("total_cartvalue")
    @field:JsonField(name = arrayOf("total_cartvalue"))
    val totalCartvalue: Float = 0.0F,

	@field:SerializedName("cart_count")
    @field:JsonField(name = arrayOf("cart_count"))
    val cartCount: Int? = 0,

    @field:SerializedName("order_model")
    @field:JsonField(name = arrayOf("order_model"))
    val order_model : Int? = null,

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

	@field:SerializedName("max_cod")
    @field:JsonField(name = arrayOf("max_cod"))
    val max_cod: String = "0",

	@field:SerializedName("default_address_id")
    @field:JsonField(name = arrayOf("default_address_id"))
    val default_address_id: String = "",

	@field:SerializedName("default_address")
    @field:JsonField(name = arrayOf("default_address"))
    val defaultAddress: DefaultAddress? = null,

    @field:SerializedName("shipping_methods")
    @field:JsonField(name = arrayOf("shipping_methods"))
    val Shippingmethods: List<shippingmethods>? = null,

    @field:SerializedName("shipping_method")
    @field:JsonField(name = arrayOf("shipping_method"))
    val shipping_method: String? = null,


    @field:SerializedName("pickup_delivery")
    @field:JsonField(name = arrayOf("pickup_delivery"))
    val pickup_delivery: String? = null
)