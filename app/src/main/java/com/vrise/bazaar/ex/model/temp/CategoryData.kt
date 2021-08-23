package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class CategoryData(

	@field:SerializedName("delivery_charge")
	@field:JsonField(name = arrayOf("delivery_charge"))
	val deliveryCharge: String? = null,

	@field:SerializedName("category_id")
	@field:JsonField(name = arrayOf("category_id"))
	val categoryId: String? = null,

	@field:SerializedName("catname")
	@field:JsonField(name = arrayOf("catname"))
	val catname: String? = null,

	@field:SerializedName("minimum_amount")
	@field:JsonField(name = arrayOf("minimum_amount"))
	val minimumAmount: String? = null,

	@field:SerializedName("catname_local")
	@field:JsonField(name = arrayOf("catname_local"))
	val catnameLocal: String? = null
)