package com.vrise.bazaar.ex.model.newmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class RecommShopItem(

	@field:SerializedName("address")
	@field:JsonField(name = arrayOf("address"))
	val address: String? = null,

	@field:SerializedName("distance")
	@field:JsonField(name = arrayOf("distance"))
	val distance: Double? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("latitude")
	@field:JsonField(name = arrayOf("latitude"))
	val latitude: String? = null,

	@field:SerializedName("rating")
	@field:JsonField(name = arrayOf("rating"))
	val rating: String? = null,

	@field:SerializedName("store_name")
	@field:JsonField(name = arrayOf("store_name"))
	val storeName: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("service_status")
	@field:JsonField(name = arrayOf("service_status"))
	val service_status: String? = null,

	@field:SerializedName("open_status")
	@field:JsonField(name = arrayOf("open_status"))
	val open_status: String? = null,

	@field:SerializedName("license")
	@field:JsonField(name = arrayOf("license"))
	val license: String? = null,

	@field:SerializedName("next_open")
	@field:JsonField(name = arrayOf("next_open"))
	val next_open: String? = null,

	@field:SerializedName("longitude")
	@field:JsonField(name = arrayOf("longitude"))
	val longitude: String? = null
)