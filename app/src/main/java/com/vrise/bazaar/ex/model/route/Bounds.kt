package com.vrise.bazaar.ex.model.route

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Bounds(

	@field:SerializedName("southwest")
	@field:JsonField(name = arrayOf("southwest"))
	val southwest: Southwest? = null,

	@field:SerializedName("northeast")
	@field:JsonField(name = arrayOf("northeast"))
	val northeast: Northeast? = null
)