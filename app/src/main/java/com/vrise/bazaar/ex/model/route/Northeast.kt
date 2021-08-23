package com.vrise.bazaar.ex.model.route


import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject


@JsonObject
data class Northeast(

	@field:SerializedName("lng")
	@field:JsonField(name = arrayOf("lng"))
	val lng: Double? = null,

	@field:SerializedName("lat")
	@field:JsonField(name = arrayOf("lat"))
	val lat: Double? = null
)