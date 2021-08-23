package com.vrise.bazaar.ex.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class SellerData(

	@field:SerializedName("pincode")
	@field:JsonField(name = arrayOf("pincode"))
	val pincode: String? = null,

	@field:SerializedName("address")
	@field:JsonField(name = arrayOf("address"))
	val address: String? = null,

	@field:SerializedName("address2")
	@field:JsonField(name = arrayOf("address2"))
	val address2: String? = null,

	@field:SerializedName("city")
	@field:JsonField(name = arrayOf("city"))
	val city: String? = null,

	@field:SerializedName("latitude")
	@field:JsonField(name = arrayOf("latitude"))
	val latitude: String? = null,

	@field:SerializedName("mobile")
	@field:JsonField(name = arrayOf("mobile"))
	val mobile: String? = null,

	@field:SerializedName("post_office")
	@field:JsonField(name = arrayOf("post_office"))
	val postOffice: String? = null,

	@field:SerializedName("store_name")
	@field:JsonField(name = arrayOf("store_name"))
	val storeName: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("longitude")
	@field:JsonField(name = arrayOf("longitude"))
	val longitude: String? = null
)