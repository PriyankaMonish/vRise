package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

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
	val sizePrice: String = "0",

	@field:SerializedName("stock")
	@field:JsonField(name = arrayOf("stock"))
	val stock: String = "0"
)