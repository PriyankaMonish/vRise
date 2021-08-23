package com.vrise.bazaar.ex.model.route

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Distance(

	@field:SerializedName("text")
	@field:JsonField(name = arrayOf("text"))
	val text: String? = null,

	@field:SerializedName("value")
	@field:JsonField(name = arrayOf("value"))
	val value: Int? = null
)