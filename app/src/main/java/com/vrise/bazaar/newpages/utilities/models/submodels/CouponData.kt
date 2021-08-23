package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class CouponData(

	@field:SerializedName("toid")
	@field:JsonField(name = arrayOf("toid"))
	val toid: String? = null,

	@field:SerializedName("coupon_title")
	@field:JsonField(name = arrayOf("coupon_title"))
	val couponTitle: String? = null,

	@field:SerializedName("no_of_use")
	@field:JsonField(name = arrayOf("no_of_use"))
	val noOfUse: String? = null,

	@field:SerializedName("valid_till")
	@field:JsonField(name = arrayOf("valid_till"))
	val validTill: String? = null,

	@field:SerializedName("code")
	@field:JsonField(name = arrayOf("code"))
	val code: String? = null,

	@field:SerializedName("min_amount")
	@field:JsonField(name = arrayOf("min_amount"))
	val minAmount: String? = null,

	@field:SerializedName("discount")
	@field:JsonField(name = arrayOf("discount"))
	val discount: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("value")
	@field:JsonField(name = arrayOf("value"))
	val value: String? = null,

	@field:SerializedName("coupon_type")
	@field:JsonField(name = arrayOf("coupon_type"))
	val couponType: String? = null,

	@field:SerializedName("products")
	@field:JsonField(name = arrayOf("products"))
	val products: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)