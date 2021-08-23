package com.vrise.bazaar.ex.model.google

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class ResultsItem(

	@field:SerializedName("formatted_address")
	@field:JsonField(name = arrayOf("formatted_address"))
	val formattedAddress: String? = null,

	@field:SerializedName("types")
	@field:JsonField(name = arrayOf("types"))
	val types: List<String?>? = null,

	@field:SerializedName("geometry")
	@field:JsonField(name = arrayOf("geometry"))
	val geometry: Geometry? = null,

	@field:SerializedName("address_components")
	@field:JsonField(name = arrayOf("address_components"))
	val addressComponents: List<AddressComponentsItem?>? = null,

	@field:SerializedName("place_id")
	@field:JsonField(name = arrayOf("place_id"))
	val placeId: String? = null,

	@field:SerializedName("plus_code")
	@field:JsonField(name = arrayOf("plus_code"))
	val plusCode: PlusCode? = null
)