package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Coprocess(

	@field:SerializedName("total")
	@field:JsonField(name = arrayOf("total"))
	val total: Double = 0.0,

	@field:SerializedName("discount_total")
	@field:JsonField(name = arrayOf("discount_total"))
	val discountTotal: Double = 0.0,

	@field:SerializedName("coupon_id")
	@field:JsonField(name = arrayOf("coupon_id"))
	val couponId: String? = null,

	@field:SerializedName("discount_amount")
	@field:JsonField(name = arrayOf("discount_amount"))
	val discountAmount: Double = 0.0,

	@field:SerializedName("cashback_amount")
	@field:JsonField(name = arrayOf("cashback_amount"))
	val cashbackAmount: Double = 0.0
)