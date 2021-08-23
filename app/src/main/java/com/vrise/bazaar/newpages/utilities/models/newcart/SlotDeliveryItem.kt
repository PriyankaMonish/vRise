package com.vrise.bazaar.newpages.utilities.models.newcart

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class SlotDeliveryItem(

	@field:SerializedName("delivery_charge")
	@field:JsonField(name = arrayOf("delivery_charge"))
	val deliveryCharge: Float? = null,

	@field:SerializedName("total_commesion")
	@field:JsonField(name = arrayOf("total_commesion"))
	val totalCommesion: String? = null,

	@field:SerializedName("delivery_type")
	@field:JsonField(name = arrayOf("delivery_type"))
	val deliveryType: String? = null,

	@field:SerializedName("slot_charge")
	@field:JsonField(name = arrayOf("slot_charge"))
	val slotCharge: String? = null,

	@field:SerializedName("grand_total")
	@field:JsonField(name = arrayOf("grand_total"))
	val grandTotal: String? = null,

	@field:SerializedName("items")
	@field:JsonField(name = arrayOf("items"))
	val items: List<ItemsItem?>? = null,

	@field:SerializedName("total_prize")
	@field:JsonField(name = arrayOf("total_prize"))
	val totalPrize: String? = null,

	@field:SerializedName("delivery_data")
	@field:JsonField(name = arrayOf("delivery_data"))
	val deliveryData: DeliveryData? = null
)