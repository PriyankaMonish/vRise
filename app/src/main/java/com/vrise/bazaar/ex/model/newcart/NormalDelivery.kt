package com.vrise.bazaar.ex.model.newcart

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class NormalDelivery(

	@field:SerializedName("delivery_charge")
	@field:JsonField(name = arrayOf("delivery_charge"))
	var deliveryCharge: Float? = null,

	@field:SerializedName("delivery_type")
	@field:JsonField(name = arrayOf("delivery_type"))
	val deliveryType: String? = null,

	@field:SerializedName("total_commesion")
	@field:JsonField(name = arrayOf("total_commesion"))
	var totalCommesion: String? = null,

	@field:SerializedName("grand_total")
	@field:JsonField(name = arrayOf("grand_total"))
	var grandTotal: String? = null,

	@field:SerializedName("items")
	@field:JsonField(name = arrayOf("items"))
	var items: List<ItemsItem?>? = null,

	@field:SerializedName("item_ids")
	@field:JsonField(name = arrayOf("item_ids"))
	var itemsid: String? = null,

	@field:SerializedName("delivery_date")
	@field:JsonField(name = arrayOf("delivery_date"))
	var deliveryDate: String? = null,

	@field:SerializedName("total_prize")
	@field:JsonField(name = arrayOf("total_prize"))
	var totalPrize: String? = null,

	@field:SerializedName("delivery_slot_id")
	@field:JsonField(name = arrayOf("delivery_slot_id"))
	var deliverySlotId: String? = null,

	@field:SerializedName("delivery_data")
	@field:JsonField(name = arrayOf("delivery_data"))
	var deliveryData: DeliveryData? = null,

	@field:SerializedName("packing_charge")
	@field:JsonField(name = arrayOf("packing_charge"))
	var packing_charge: String? = null

) : Serializable