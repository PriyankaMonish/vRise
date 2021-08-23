package com.vrise.bazaar.ex.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class StatusData(

	@field:SerializedName("seller")
	@field:JsonField(name = arrayOf("seller"))
	val seller: String? = null,

	@field:SerializedName("deliveryboy")
	@field:JsonField(name = arrayOf("deliveryboy"))
	val deliveryboy: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	var id: String? = null,

	@field:SerializedName("time_line")
	@field:JsonField(name = arrayOf("time_line"))
	var timeLine: List<TimeLineItem?>? = null,

	@field:SerializedName("delivery_status")
	@field:JsonField(name = arrayOf("delivery_status"))
	val deliveryStatus: String? = null,

	@field:SerializedName("seller_status")
	@field:JsonField(name = arrayOf("seller_status"))
	val sellerStatus: String? = null
)