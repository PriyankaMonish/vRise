package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.temp.DeliverySlotData
import com.vrise.bazaar.ex.model.temp.OrdProductsItem

class RequestedOrder (

    @field:SerializedName("code")
    @field:JsonField(name = arrayOf("code"))
    val code: String? = null,

    @field:SerializedName("saleid")
    @field:JsonField(name = arrayOf("saleid"))
    val saleid: String? = null,

    @field:SerializedName("item_count")
    @field:JsonField(name = arrayOf("item_count"))
    val item_count: Int? = null,

    @field:SerializedName("myreview")
    @field:JsonField(name = arrayOf("myreview"))
    val myreview: Any? = null,

    @field:SerializedName("discount_amount")
    @field:JsonField(name = arrayOf("discount_amount"))
    val discountAmount: String? = null,

    @field:SerializedName("payment_status")
    @field:JsonField(name = arrayOf("payment_status"))
    val paymentStatus: String? = null,

    @field:SerializedName("cod_due")
    @field:JsonField(name = arrayOf("cod_due"))
    val codDue: String? = null,

    @field:SerializedName("spot_delivery")
    @field:JsonField(name = arrayOf("spot_delivery"))
    val spotDelivery: String? = null,


    @field:SerializedName("order_date")
    @field:JsonField(name = arrayOf("order_date"))
    val orderDate: String? = null,

    @field:SerializedName("delivery_date")
    @field:JsonField(name = arrayOf("delivery_date"))
    val deliveryDate: String? = null,

    @field:SerializedName("delivery_charge")
    @field:JsonField(name = arrayOf("delivery_charge"))
    val deliveryCharge: Float? = null,

    @field:SerializedName("packing_charge")
    @field:JsonField(name = arrayOf("packing_charge"))
    val packing_charge: Int? = null,

    @field:SerializedName("user_id")
    @field:JsonField(name = arrayOf("user_id"))
    val userId: String? = null,

    @field:SerializedName("delivery_type")
    @field:JsonField(name = arrayOf("delivery_type"))
    val deliveryType: String? = null,

    @field:SerializedName("delivery_slot_data")
    @field:JsonField(name = arrayOf("delivery_slot_data"))
    val deliverySlotData: DeliverySlotData? = null,

    @field:SerializedName("store_name")
    @field:JsonField(name = arrayOf("store_name"))
    val storeName: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("product_list")
    @field:JsonField(name = arrayOf("product_list"))
    val productList: List<Any?>? = null,

    @field:SerializedName("grand_total")
    @field:JsonField(name = arrayOf("grand_total"))
    val grandTotal: String? = null,

    @field:SerializedName("store_img")
    @field:JsonField(name = arrayOf("store_img"))
    val storeImg: String = "",

    @field:SerializedName("ord_products")
    @field:JsonField(name = arrayOf("ord_products"))
    val ordProducts: List<OrdProductsItem?>? = null,

    @field:SerializedName("total_prize")
    @field:JsonField(name = arrayOf("total_prize"))
    val totalPrize: String? = null,

    @field:SerializedName("delivery_status")
    @field:JsonField(name = arrayOf("delivery_status"))
    val deliveryStatus: String? = null,

    @field:SerializedName("seller_id")
    @field:JsonField(name = arrayOf("seller_id"))
    val sellerId: String? = null,

    @field:SerializedName("wallet_amount")
    @field:JsonField(name = arrayOf("wallet_amount"))
    val walletAmount: String? = null

    ,

    @field:SerializedName("pickup_charge")
    @field:JsonField(name = arrayOf("pickup_charge"))
    val pickup_charge: Int? = null,

    @field:SerializedName("seller_status")
    @field:JsonField(name = arrayOf("seller_status"))
    val seller_status: String? = null,

    @field:SerializedName("customer_status")
    @field:JsonField(name = arrayOf("customer_status"))
    val customer_status: String? = null,

    @field:SerializedName("pickup_order")
    @field:JsonField(name = arrayOf("pickup_order"))
    val pickup_order: String? = null,

    @field:SerializedName("store_phone")
    @field:JsonField(name = arrayOf("store_phone"))
    val store_phone: String? = null,

    @field:SerializedName("store_lat")
    @field:JsonField(name = arrayOf("store_lat"))
    val store_lat: String? = null,

    @field:SerializedName("store_long")
    @field:JsonField(name = arrayOf("store_long"))
    val store_long: String? = null,

    @field:SerializedName("order_model")
    @field:JsonField(name = arrayOf("order_model"))
    val order_model: Int? = null,



    var btnEnabled:Boolean=false
)
