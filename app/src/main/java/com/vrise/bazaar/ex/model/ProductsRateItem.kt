package com.vrise.bazaar.ex.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class ProductsRateItem(

	@field:SerializedName("product_review")
	@field:JsonField(name = arrayOf("product_review"))
	val productReview: String? = null,

	@field:SerializedName("seller_product_id")
	@field:JsonField(name = arrayOf("seller_product_id"))
	val sellerProductId: String? = null,

	@field:SerializedName("product_rating")
	@field:JsonField(name = arrayOf("product_rating"))
	val productRating: String? = null
)