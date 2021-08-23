package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class ItemsItem(

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String? = null,

    @field:SerializedName("catname")
    @field:JsonField(name = arrayOf("catname"))
    val catname: String? = null,

    @field:SerializedName("catcommission")
    @field:JsonField(name = arrayOf("catcommission"))
    val catcommission: String? = null,

    @field:SerializedName("catcommission_type")
    @field:JsonField(name = arrayOf("catcommission_type"))
    val catcommissionType: String? = null,

    @field:SerializedName("discount")
    @field:JsonField(name = arrayOf("discount"))
    val discount: String? = null,

    @field:SerializedName("minimum_amount")
    @field:JsonField(name = arrayOf("minimum_amount"))
    val minimumAmount: String? = null,

    @field:SerializedName("seller_product_id")
    @field:JsonField(name = arrayOf("seller_product_id"))
    val sellerProductId: String? = null,

    @field:SerializedName("product_quantity")
    @field:JsonField(name = arrayOf("product_quantity"))
    val productQuantity: String? = null,

    @field:SerializedName("subcategory_id")
    @field:JsonField(name = arrayOf("subcategory_id"))
    val subcategoryId: String? = null,

    @field:SerializedName("category_id")
    @field:JsonField(name = arrayOf("category_id"))
    val categoryId: String? = null,

    @field:SerializedName("delivery_type")
    @field:JsonField(name = arrayOf("delivery_type"))
    val deliveryType: String? = null,

    @field:SerializedName("price")
    @field:JsonField(name = arrayOf("price"))
    val price: String? = null,

    @field:SerializedName("commission")
    @field:JsonField(name = arrayOf("commission"))
    val commission: String? = null,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String? = null,

    @field:SerializedName("catname_local")
    @field:JsonField(name = arrayOf("catname_local"))
    val catnameLocal: String? = null,

    @field:SerializedName("commission_type")
    @field:JsonField(name = arrayOf("commission_type"))
    val commissionType: String? = null,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String? = null,

    @field:SerializedName("ecom_product_id")
    @field:JsonField(name = arrayOf("ecom_product_id"))
    val ecomProductId: String? = null,

    @field:SerializedName("delivery_slot_id")
    @field:JsonField(name = arrayOf("delivery_slot_id"))
    val deliverySlotId: String? = null,

    @field:SerializedName("sale_price")
    @field:JsonField(name = arrayOf("sale_price"))
    val salePrice: String? = null,

    @field:SerializedName("delivery_date")
    @field:JsonField(name = arrayOf("delivery_date"))
    val deliveryDate: String? = null,

    @field:SerializedName("delivery_charge")
    @field:JsonField(name = arrayOf("delivery_charge"))
    val deliveryCharge: String? = null,

    @field:SerializedName("daily_product")
    @field:JsonField(name = arrayOf("daily_product"))
    val dailyProduct: String? = null,

    @field:SerializedName("size")
    @field:JsonField(name = arrayOf("size"))
    val size: String? = null,

    @field:SerializedName("user_id")
    @field:JsonField(name = arrayOf("user_id"))
    val userId: String? = null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

    @field:SerializedName("brand_id")
    @field:JsonField(name = arrayOf("brand_id"))
    val brandId: String? = null,
	
    @field:SerializedName("brand")
    @field:JsonField(name = arrayOf("brand"))
    val brand: String? = null,

    @field:SerializedName("updated_date")
    @field:JsonField(name = arrayOf("updated_date"))
    val updatedDate: String? = null,

    @field:SerializedName("product_size_id")
    @field:JsonField(name = arrayOf("product_size_id"))
    val productSizeId: String? = null,

    @field:SerializedName("veg_non")
    @field:JsonField(name = arrayOf("veg_non"))
    val vegNon: String? = null,

    @field:SerializedName("total_sale_price")
    @field:JsonField(name = arrayOf("total_sale_price"))
    val total_sale_price: String? = null,

    @field:SerializedName("next_available")
    @field:JsonField(name = arrayOf("next_available"))
    val nextAvailable: String? = null,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String? = null
)