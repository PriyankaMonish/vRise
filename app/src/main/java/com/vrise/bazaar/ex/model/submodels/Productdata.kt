package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Productdata(

    @field:SerializedName("delivery_slots")
    @field:JsonField(name = arrayOf("delivery_slots"))
    val deliverySlots: String? = null,

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String? = null,

    @field:SerializedName("veg_non")
    @field:JsonField(name = arrayOf("veg_non"))
    val vegNon: String? = null,

    @field:SerializedName("brand")
    @field:JsonField(name = arrayOf("brand"))
    val brand: String? = null,

    @field:SerializedName("brand_id")
    @field:JsonField(name = arrayOf("brand_id"))
    val brandId: String? = null,

    @field:SerializedName("subcategory_local")
    @field:JsonField(name = arrayOf("subcategory_local"))
    val subcategoryLocal: String? = null,

    @field:SerializedName("available_sizes")
    @field:JsonField(name = arrayOf("available_sizes"))
    val availableSizes: String? = null,

    @field:SerializedName("product_status")
    @field:JsonField(name = arrayOf("product_status"))
    val productStatus: String? = null,

    @field:SerializedName("description")
    @field:JsonField(name = arrayOf("description"))
    val description: String? = null,

    @field:SerializedName("delivery_times")
    @field:JsonField(name = arrayOf("delivery_times"))
    val deliveryTimes: ArrayList<DeliveryTimesItem?>? = null,

    @field:SerializedName("subcategory_id")
    @field:JsonField(name = arrayOf("subcategory_id"))
    val subcategoryId: String? = null,

    @field:SerializedName("category_id")
    @field:JsonField(name = arrayOf("category_id"))
    val categoryId: String? = null,

    @field:SerializedName("store_name")
    @field:JsonField(name = arrayOf("store_name"))
    val storeName: String? = null,

    @field:SerializedName("commission")
    @field:JsonField(name = arrayOf("commission"))
    val commission: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("category_local")
    @field:JsonField(name = arrayOf("category_local"))
    val categoryLocal: String? = null,

    @field:SerializedName("seller_id")
    @field:JsonField(name = arrayOf("seller_id"))
    val sellerId: String? = null,

    @field:SerializedName("commission_type")
    @field:JsonField(name = arrayOf("commission_type"))
    val commissionType: String? = null,

    @field:SerializedName("sprdtid")
    @field:JsonField(name = arrayOf("sprdtid"))
    val sprdtid: String? = null,

    @field:SerializedName("size_type")
    @field:JsonField(name = arrayOf("size_type"))
    val sizeType: String? = null,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String? = null,

    @field:SerializedName("delivery_note")
    @field:JsonField(name = arrayOf("delivery_note"))
    val deliveryNote: String? = null,

    @field:SerializedName("spot_delivery")
    @field:JsonField(name = arrayOf("spot_delivery"))
    val spotDelivery: Int = 0,

    @field:SerializedName("fav_status")
    @field:JsonField(name = arrayOf("fav_status"))
    var favStatus: Int? = null,

    @field:SerializedName("stock_type")
    @field:JsonField(name = arrayOf("stock_type"))
    val stockType: String? = null,

    @field:SerializedName("size_stock_price")
    @field:JsonField(name = arrayOf("size_stock_price"))
    val sizeStockPrice: ArrayList<SizeStockPriceItem?>? = null,

    @field:SerializedName("daily_product")
    @field:JsonField(name = arrayOf("daily_product"))
    val dailyProduct: String? = null,

    @field:SerializedName("available_status")
    @field:JsonField(name = arrayOf("available_status"))
    val availableStatus: String? = null,

    @field:SerializedName("shop")
    @field:JsonField(name = arrayOf("shop"))
    val shopa: String = "",

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

    @field:SerializedName("category")
    @field:JsonField(name = arrayOf("category"))
    val category: String? = null,

    @field:SerializedName("subcategory")
    @field:JsonField(name = arrayOf("subcategory"))
    val subcategory: String? = null,

    @field:SerializedName("schedule_status")
    @field:JsonField(name = arrayOf("schedule_status"))
    val schedule_status: String? = null,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null,

    @field:SerializedName("color")
    @field:JsonField(name = arrayOf("color"))
    val color: String? = null,

    @field:SerializedName("extrafields")
    @field:JsonField(name = arrayOf("extrafields"))
    val extrafields: ArrayList<extrafields?>? = null,

    @field:SerializedName("color_list")
    @field:JsonField(name = arrayOf("color_list"))
    val color_list: ArrayList<Colorlist?>? = null,

    @field:SerializedName("pickup")
    @field:JsonField(name = arrayOf("pickup"))
    val pickup: String? = null,

    @field:SerializedName("seller_shipping_method")
    @field:JsonField(name = arrayOf("seller_shipping_method"))
    val seller_shipping_method: String? = null,

    @field:SerializedName("age")
    @field:JsonField(name = arrayOf("age"))
    val age: String? = null,

    @field:SerializedName("weight")
    @field:JsonField(name = arrayOf("weight"))
    val weight: String? = null
)