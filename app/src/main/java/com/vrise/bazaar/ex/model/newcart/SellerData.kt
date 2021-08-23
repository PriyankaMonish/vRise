package com.vrise.bazaar.ex.model.newcart

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class SellerData(

	@field:SerializedName("mobile")
	@field:JsonField(name = arrayOf("mobile"))
	val mobile: String? = null,

    @field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("store_name")
	@field:JsonField(name = arrayOf("store_name"))
	val storeName: String? = null,

	@field:SerializedName("email")
	@field:JsonField(name = arrayOf("email"))
	val email: String? = null,

	@field:SerializedName("packing_charge")
	@field:JsonField(name = arrayOf("packing_charge"))
	val packing_charge: String? = "0",


	@field:SerializedName("pickup_charge_option")
	@field:JsonField(name = arrayOf("pickup_charge_option"))
	val pickup_charge_option: String? = "0",

	@field:SerializedName("pickup_charge")
	@field:JsonField(name = arrayOf("pickup_charge"))
	val pickup_charge: String? = "0"

): Serializable