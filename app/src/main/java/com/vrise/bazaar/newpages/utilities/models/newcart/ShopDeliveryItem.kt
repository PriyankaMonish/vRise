package com.vrise.bazaar.newpages.utilities.models.newcart

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class ShopDeliveryItem(

	@field:SerializedName("seller")
	@field:JsonField(name = arrayOf("seller"))
	val seller: String? = null,

	@field:SerializedName("normal_delivery")
	@field:JsonField(name = arrayOf("normal_delivery"))
	val normalDelivery: NormalDelivery? = null,

	@field:SerializedName("seller_data")
	@field:JsonField(name = arrayOf("seller_data"))
	val sellerData: SellerData? = null,

	@field:SerializedName("total_sales")
	@field:JsonField(name = arrayOf("total_sales"))
	val totalSales: String? = null,

	@field:SerializedName("slot_delivery")
	@field:JsonField(name = arrayOf("slot_delivery"))
	val slotDelivery: List<SlotDeliveryItem?>? = null,

	@field:SerializedName("spot_delivery")
	@field:JsonField(name = arrayOf("spot_delivery"))
	val spotDelivery: SpotDelivery? = null
)