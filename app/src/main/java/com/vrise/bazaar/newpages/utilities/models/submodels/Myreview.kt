package com.vrise.bazaar.newpages.utilities.models.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Myreview(

	@field:SerializedName("date")
	@field:JsonField(name = arrayOf("date"))
	val date: String? = null,

	@field:SerializedName("user_id")
	@field:JsonField(name = arrayOf("user_id"))
	val userId: String? = null,

	@field:SerializedName("review")
	@field:JsonField(name = arrayOf("review"))
	val review: String? = null,

	@field:SerializedName("rating")
	@field:JsonField(name = arrayOf("rating"))
	val rating: String = "0",

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("seller_product_id")
	@field:JsonField(name = arrayOf("seller_product_id"))
	val sellerProductId: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)