package com.vrise.bazaar.ex.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class DeliveryboyData(

	@field:SerializedName("fname")
	@field:JsonField(name = arrayOf("fname"))
	val fname: String? = null,

	@field:SerializedName("lname")
	@field:JsonField(name = arrayOf("lname"))
	val lname: String? = null,
	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("phone")
	@field:JsonField(name = arrayOf("phone"))
	val phone: String? = null,

	@field:SerializedName("vehicle_no")
	@field:JsonField(name = arrayOf("vehicle_no"))
	val vehicleNo: String? = null,

	@field:SerializedName("latitude")
	@field:JsonField(name = arrayOf("latitude"))
	val latitude: String? = null,

	@field:SerializedName("model")
	@field:JsonField(name = arrayOf("model"))
	val model: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("email")
	@field:JsonField(name = arrayOf("email"))
	val email: String? = null,

	@field:SerializedName("longitude")
	@field:JsonField(name = arrayOf("longitude"))
	val longitude: String? = null
)