package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class CrtListItem(

	@field:SerializedName("delivery_charge")
	@field:JsonField(name = arrayOf("delivery_charge"))
	val deliveryCharge: String? = null,

	@field:SerializedName("delivery_type")
	@field:JsonField(name = arrayOf("delivery_type"))
	val deliveryType: String? = null,

	@field:SerializedName("grand_total")
	@field:JsonField(name = arrayOf("grand_total"))
	val grandTotal: Double? = null,

	@field:SerializedName("items")
	@field:JsonField(name = arrayOf("items"))
	val items: List<ItemsItem?>? = null,

	@field:SerializedName("total_prize")
	@field:JsonField(name = arrayOf("total_prize"))
	val totalPrize: Double? = null,

	@field:SerializedName("delivery_data")
	@field:JsonField(name = arrayOf("delivery_data"))
	val deliveryData: DeliveryDataItem? = null
)