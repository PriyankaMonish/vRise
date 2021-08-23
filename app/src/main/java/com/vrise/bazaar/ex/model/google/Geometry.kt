package com.vrise.bazaar.ex.model.google

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Geometry(

	@field:SerializedName("viewport")
	@field:JsonField(name = arrayOf("viewport"))
	val viewport: Viewport? = null,

	@field:SerializedName("bounds")
	@field:JsonField(name = arrayOf("bounds"))
	val bounds: Bounds? = null,

	@field:SerializedName("location")
	@field:JsonField(name = arrayOf("location"))
	val location: Location? = null,

	@field:SerializedName("location_type")
	@field:JsonField(name = arrayOf("location_type"))
	val locationType: String? = null
)