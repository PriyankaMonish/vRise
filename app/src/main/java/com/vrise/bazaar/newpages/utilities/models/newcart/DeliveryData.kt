package com.vrise.bazaar.newpages.utilities.models.newcart

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class DeliveryData(

	@field:SerializedName("start_time")
	@field:JsonField(name = arrayOf("start_time"))
	val startTime: String? = null,

	@field:SerializedName("charge")
	@field:JsonField(name = arrayOf("charge"))
	val charge: String? = null,

	@field:SerializedName("end_time")
	@field:JsonField(name = arrayOf("end_time"))
	val endTime: String? = null,

	@field:SerializedName("slot_description")
	@field:JsonField(name = arrayOf("slot_description"))
	val slotDescription: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("delivery_slot_id")
	@field:JsonField(name = arrayOf("delivery_slot_id"))
	val deliverySlotId: String? = null,

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