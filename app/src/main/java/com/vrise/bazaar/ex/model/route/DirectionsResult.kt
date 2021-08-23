package com.vrise.bazaar.ex.model.route

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class DirectionsResult(

	@field:SerializedName("routes")
	@field:JsonField(name = arrayOf("routes"))
	val routes: List<RoutesItem?>? = null,

	@field:SerializedName("geocoded_waypoints")
	@field:JsonField(name = arrayOf("geocoded_waypoints"))
	val geocodedWaypoints: List<GeocodedWaypointsItem?>? = null
)