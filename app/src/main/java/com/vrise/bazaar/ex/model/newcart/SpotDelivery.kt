package com.vrise.bazaar.ex.model.newcart

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class SpotDelivery(

    @field:SerializedName("delivery_charge")
	@field:JsonField(name = arrayOf("delivery_charge"))
	var deliveryCharge: Int? = null,

	@field:SerializedName("total_commesion")
	@field:JsonField(name = arrayOf("total_commesion"))
	var totalCommesion: String? = null,

    @field:SerializedName("delivery_type")
	@field:JsonField(name = arrayOf("delivery_type"))
	val deliveryType: Int? = null,

    @field:SerializedName("grand_total")
	@field:JsonField(name = arrayOf("grand_total"))
	var grandTotal: Int? = null,

    @field:SerializedName("items")
	@field:JsonField(name = arrayOf("items"))
	var items: List<ItemsItem?>? = null,

	@field:SerializedName("item_ids")
	@field:JsonField(name = arrayOf("item_ids"))
	var itemids: String? = null,

	@field:SerializedName("delivery_date")
	@field:JsonField(name = arrayOf("delivery_date"))
	var deliveryDate: String? = null,

	@field:SerializedName("delivery_data")
	@field:JsonField(name = arrayOf("delivery_data"))
	var deliveryData: DeliveryData? = null,

	@field:SerializedName("delivery_slot_id")
	@field:JsonField(name = arrayOf("delivery_slot_id"))
	var deliverySlotId: String? = null,

	@field:SerializedName("total_prize")
	@field:JsonField(name = arrayOf("total_prize"))
	var totalPrize: Int? = null,

	@field:SerializedName("packing_charge")
	@field:JsonField(name = arrayOf("packing_charge"))
	var packing_charge: Int? = null,

	@field:SerializedName("seller_pickup_discount")
	@field:JsonField(name = arrayOf("seller_pickup_discount"))
	var seller_pickup_discount: String? = null,

	@field:SerializedName("seller_pickup_charge")
	@field:JsonField(name = arrayOf("seller_pickup_charge"))
	var seller_pickup_charge: String? = null

//	@field:SerializedName("seller_pickup_offer")
//	@field:JsonField(name = arrayOf("seller_pickup_offer"))
//	var seller_pickup_offer: PickupOffer? = null

): Serializable