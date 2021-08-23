package com.vrise.bazaar.newpages.utilities.models.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class ProductData(

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String? = null,

    @field:SerializedName("catname")
    @field:JsonField(name = arrayOf("catname"))
    val catname: String? = null,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String? = null,

    @field:SerializedName("ecom_product_id")
    @field:JsonField(name = arrayOf("ecom_product_id"))
    val ecomProductId: String? = null,

    @field:SerializedName("minimum_amount")
    @field:JsonField(name = arrayOf("minimum_amount"))
    val minimumAmount: String? = null,

    @field:SerializedName("seller_product_id")
    @field:JsonField(name = arrayOf("seller_product_id"))
    val sellerProductId: String? = null,

    @field:SerializedName("subcategory_id")
    @field:JsonField(name = arrayOf("subcategory_id"))
    val subcategoryId: String? = null,

    @field:SerializedName("category_id")
    @field:JsonField(name = arrayOf("category_id"))
    val categoryId: String? = null,

    @field:SerializedName("daily_product")
    @field:JsonField(name = arrayOf("daily_product"))
    val dailyProduct: String? = null,

    @field:SerializedName("size")
    @field:JsonField(name = arrayOf("size"))
    val size: String? = null,

    @field:SerializedName("price")
    @field:JsonField(name = arrayOf("price"))
    val price: String? = null,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String? = null,

    @field:SerializedName("commission")
    @field:JsonField(name = arrayOf("commission"))
    val commission: String? = null,

    @field:SerializedName("sale_price")
    @field:JsonField(name = arrayOf("sale_price"))
    val saleprices: String? = null,

    @field:SerializedName("commission_type")
    @field:JsonField(name = arrayOf("commission_type"))
    val commissionType: String? = null
)