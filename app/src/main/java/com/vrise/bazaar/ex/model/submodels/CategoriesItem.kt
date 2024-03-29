package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class CategoriesItem(

	@field:SerializedName("name_local")
	@field:JsonField(name = arrayOf("name_local"))
	val nameLocal: String? = null,

	@field:SerializedName("delivery_charge")
	@field:JsonField(name = arrayOf("delivery_charge"))
	val deliveryCharge: Float? = null,

	@field:SerializedName("daily_product")
	@field:JsonField(name = arrayOf("daily_product"))
	val dailyProduct: String? = null,

	@field:SerializedName("image_link")
	@field:JsonField(name = arrayOf("image_link"))
	val imagelink: String? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("description")
	@field:JsonField(name = arrayOf("description"))
	val description: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("minimum_amount")
	@field:JsonField(name = arrayOf("minimum_amount"))
	val minimumAmount: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)