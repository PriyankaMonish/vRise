package com.vrise.bazaar.ex.model.route


import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject


@JsonObject
data class OverviewPolyline(

	@field:SerializedName("points")
	@field:JsonField(name = arrayOf("points"))
	val points: String? = null
)