package com.vrise.bazaar.ex.model.newmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class SizeStockPriceItem(

	@field:SerializedName("size_name")
	@field:JsonField(name = arrayOf("size_name"))
	val sizeName: String? = null,

	@field:SerializedName("size_id")
	@field:JsonField(name = arrayOf("size_id"))
	val sizeId: String? = null,

	@field:SerializedName("size_price")
	@field:JsonField(name = arrayOf("size_price"))
	val sizePrice: Float? = null,

	@field:SerializedName("stock")
	@field:JsonField(name = arrayOf("stock"))
	val stock: String? = null,

	@field:SerializedName("pickup_offer_price")
	@field:JsonField(name = arrayOf("pickup_offer_price"))
	val pickup_offer_price: Int? = null,

	@field:SerializedName("pickup_discount")
	@field:JsonField(name = arrayOf("pickup_discount"))
	val pickup_discount: String? = null,

	var selectedPosition: Int? = null
)