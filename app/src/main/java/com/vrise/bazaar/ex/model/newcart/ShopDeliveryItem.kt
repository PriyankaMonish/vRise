package com.vrise.bazaar.ex.model.newcart

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class ShopDeliveryItem(

	@field:SerializedName("seller")
	@field:JsonField(name = arrayOf("seller"))
	var seller: String? = null,

	@field:SerializedName("normal_delivery")
	@field:JsonField(name = arrayOf("normal_delivery"))
	var normalDelivery: NormalDelivery? = null,

	@field:SerializedName("seller_data")
	@field:JsonField(name = arrayOf("seller_data"))
	val sellerData: SellerData? = null,

	@field:SerializedName("total_sales")
	@field:JsonField(name = arrayOf("total_sales"))
	val totalSales: Int? = null,

	@field:SerializedName("slot_delivery")
	@field:JsonField(name = arrayOf("slot_delivery"))
	var slotDelivery: List<SlotDeliveryItem?>? = null,

	@field:SerializedName("spot_delivery")
	@field:JsonField(name = arrayOf("spot_delivery"))
	var spotDelivery: SpotDelivery? = null


) : Serializable