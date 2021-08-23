package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class ShopCategory(

    @field:SerializedName("commission")
    @field:JsonField(name = arrayOf("commission"))
    val commission: String,

    @field:SerializedName("commission_type")
    @field:JsonField(name = arrayOf("commission_type"))
    val commissionType: String,

    @field:SerializedName("daily_product")
    @field:JsonField(name = arrayOf("daily_product"))
    val dailyProduct: String,

    @field:SerializedName("delivery_charge")
    @field:JsonField(name = arrayOf("delivery_charge"))
    val deliveryCharge: String,

    @field:SerializedName("description")
    @field:JsonField(name = arrayOf("description"))
    val description: String,

    @field:SerializedName("fav_status")
    @field:JsonField(name = arrayOf("fav_status"))
    val favStatus: String,

    @field:SerializedName("id")
    @field:JsonField(name = arrayOf("id"))
    val id: String,

    @field:SerializedName("img_link")
    @field:JsonField(name = arrayOf("img_link"))
    val imgLink: String,

    @field:SerializedName("minimum_amount")
    @field:JsonField(name = arrayOf("minimum_amount"))
    val minimumAmount: String,

    @field:SerializedName("name")
    @field:JsonField(name = arrayOf("name"))
    val name: String,

    @field:SerializedName("name_local")
    @field:JsonField(name = arrayOf("name_local"))
    val nameLocal: String,

    @field:SerializedName("prefer_list")
    @field:JsonField(name = arrayOf("prefer_list"))
    val preferList: Int,

    @field:SerializedName("status")
    @field:JsonField(name = arrayOf("status"))
    val status: String,

    @field:SerializedName("sub_categories")
    @field:JsonField(name = arrayOf("sub_categories"))
    val subCategories: List<Any?>?

)