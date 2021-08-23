package com.vrise.bazaar.ex.model.route

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class StepsItem(

	@field:SerializedName("duration")
	@field:JsonField(name = arrayOf("duration"))
	val duration: Duration? = null,

	@field:SerializedName("start_location")
	@field:JsonField(name = arrayOf("start_location"))
	val startLocation: StartLocation? = null,

	@field:SerializedName("steps")
	@field:JsonField(name = arrayOf("steps"))
	val steps: List<StepsItem?>? = null,

	@field:SerializedName("distance")
	@field:JsonField(name = arrayOf("distance"))
	val distance: Distance? = null,

	@field:SerializedName("travel_mode")
	@field:JsonField(name = arrayOf("travel_mode"))
	val travelMode: String? = null,

	@field:SerializedName("html_instructions")
	@field:JsonField(name = arrayOf("html_instructions"))
	val htmlInstructions: String? = null,

	@field:SerializedName("end_location")
	@field:JsonField(name = arrayOf("end_location"))
	val endLocation: EndLocation? = null,

	@field:SerializedName("polyline")
	@field:JsonField(name = arrayOf("polyline"))
	val polyline: Polyline? = null
)