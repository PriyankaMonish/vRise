package com.vrise.bazaar.ex.model.route


import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject


@JsonObject
data class RoutesItem(

	@field:SerializedName("copyrights")
	@field:JsonField(name = arrayOf("copyrights"))
	val copyrights: String? = null,

	@field:SerializedName("legs")
	@field:JsonField(name = arrayOf("legs"))
	val legs: List<LegsItem?>? = null,

	@field:SerializedName("bounds")
	@field:JsonField(name = arrayOf("bounds"))
	val bounds: Bounds? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)