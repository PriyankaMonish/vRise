package com.vrise.bazaar.ex.model.google

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class AddressComponentsItem(

	@field:SerializedName("types")
	@field:JsonField(name = arrayOf("types"))
	val types: List<String?>? = null,

	@field:SerializedName("short_name")
	@field:JsonField(name = arrayOf("short_name"))
	val shortName: String? = null,

	@field:SerializedName("long_name")
	@field:JsonField(name = arrayOf("long_name"))
	val longName: String? = null
)