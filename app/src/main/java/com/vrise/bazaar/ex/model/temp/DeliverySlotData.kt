package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class DeliverySlotData(

	@field:SerializedName("start_time")
	@field:JsonField(name = arrayOf("start_time"))
	val startTime: String? = null,

	@field:SerializedName("charge")
	@field:JsonField(name = arrayOf("charge"))
	val charge: String? = null,

	@field:SerializedName("sub_distance")
	@field:JsonField(name = arrayOf("sub_distance"))
	val subDistance: String? = null,

	@field:SerializedName("main_distance")
	@field:JsonField(name = arrayOf("main_distance"))
	val mainDistance: String? = null,

	@field:SerializedName("end_time")
	@field:JsonField(name = arrayOf("end_time"))
	val endTime: String? = null,

	@field:SerializedName("slot_description")
	@field:JsonField(name = arrayOf("slot_description"))
	val slotDescription: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("sub_charge")
	@field:JsonField(name = arrayOf("sub_charge"))
	val subCharge: String? = null,

	@field:SerializedName("slot_name")
	@field:JsonField(name = arrayOf("slot_name"))
	val slotName: String? = null,

	@field:SerializedName("spot_delivery")
	@field:JsonField(name = arrayOf("spot_delivery"))
	val spotDelivery: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)