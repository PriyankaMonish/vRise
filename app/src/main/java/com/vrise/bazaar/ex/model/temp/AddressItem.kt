package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class AddressItem(

	@field:SerializedName("pincode")
	@field:JsonField(name = arrayOf("pincode"))
	val pincode: String? = null,

	@field:SerializedName("address_type")
	@field:JsonField(name = arrayOf("address_type"))
	val addr: String? = null,

	@field:SerializedName("address")
	@field:JsonField(name = arrayOf("address"))
	val address: String? = null,

	@field:SerializedName("address2")
	@field:JsonField(name = arrayOf("address2"))
	val address2: String? = null,

	@field:SerializedName("latitude")
	@field:JsonField(name = arrayOf("latitude"))
	val latitude: String? = null,

	@field:SerializedName("post_office")
	@field:JsonField(name = arrayOf("post_office"))
	val postOffice: String? = null,

	@field:SerializedName("default_address")
	@field:JsonField(name = arrayOf("default_address"))
	val defaultAddress: String? = null,

	@field:SerializedName("user_id")
	@field:JsonField(name = arrayOf("user_id"))
	val userId: String? = null,

	@field:SerializedName("district")
	@field:JsonField(name = arrayOf("district"))
	val district: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("state")
	@field:JsonField(name = arrayOf("state"))
	val state: String? = null,

	@field:SerializedName("updated_date")
	@field:JsonField(name = arrayOf("updated_date"))
	val updatedDate: String? = null,

	@field:SerializedName("landmark")
	@field:JsonField(name = arrayOf("landmark"))
	val landmark: String? = null,

	@field:SerializedName("longitude")
	@field:JsonField(name = arrayOf("longitude"))
	val longitude: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)