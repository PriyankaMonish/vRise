package com.vrise.bazaar.ex.model.route


import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject


@JsonObject
data class GeocodedWaypointsItem(

	@field:SerializedName("types")
	@field:JsonField(name = arrayOf("types"))
	val types: List<String?>? = null,

	@field:SerializedName("geocoder_status")
	@field:JsonField(name = arrayOf("geocoder_status"))
	val geocoderStatus: String? = null,

	@field:SerializedName("partial_match")
	@field:JsonField(name = arrayOf("partial_match"))
	val partialMatch: Boolean? = null,

	@field:SerializedName("place_id")
	@field:JsonField(name = arrayOf("place_id"))
	val placeId: String? = null
)