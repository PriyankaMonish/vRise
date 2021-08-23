package com.vrise.bazaar.ex.model.route

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class LegsItem(

	@field:SerializedName("duration")
	@field:JsonField(name = arrayOf("duration"))
	val duration: Duration? = null,

	@field:SerializedName("summary")
	@field:JsonField(name = arrayOf("summary"))
	val summary: String? = null,

	@field:SerializedName("start_location")
	@field:JsonField(name = arrayOf("start_location"))
	val startLocation: StartLocation? = null,

	@field:SerializedName("steps")
	@field:JsonField(name = arrayOf("steps"))
	val steps: List<StepsItem?>? = null,

	@field:SerializedName("distance")
	@field:JsonField(name = arrayOf("distance"))
	val distance: Distance? = null,

	@field:SerializedName("start_address")
	@field:JsonField(name = arrayOf("start_address"))
	val startAddress: String? = null,

	@field:SerializedName("warnings")
	@field:JsonField(name = arrayOf("warnings"))
	val warnings: List<Any?>? = null,

	@field:SerializedName("end_location")
	@field:JsonField(name = arrayOf("end_location"))
	val endLocation: EndLocation? = null,

	@field:SerializedName("end_address")
	@field:JsonField(name = arrayOf("end_address"))
	val endAddress: String? = null,

	@field:SerializedName("overview_polyline")
	@field:JsonField(name = arrayOf("overview_polyline"))
	val overviewPolyline: OverviewPolyline? = null,

	@field:SerializedName("waypoint_order")
	@field:JsonField(name = arrayOf("waypoint_order"))
	val waypointOrder: List<Any?>? = null
)