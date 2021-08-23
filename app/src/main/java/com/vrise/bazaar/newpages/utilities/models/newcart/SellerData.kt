package com.vrise.bazaar.newpages.utilities.models.newcart

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class SellerData(

	@field:SerializedName("mobile")
	@field:JsonField(name = arrayOf("mobile"))
	val mobile: String? = null,

	@field:SerializedName("store_name")
	@field:JsonField(name = arrayOf("store_name"))
	val storeName: String? = null,

	@field:SerializedName("email")
	@field:JsonField(name = arrayOf("email"))
	val email: String? = null
)