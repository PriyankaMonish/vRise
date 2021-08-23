package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Due(

	@field:SerializedName("due")
	@field:JsonField(name = arrayOf("due"))
	val due: String? = null,

	@field:SerializedName("subscriber_id")
	@field:JsonField(name = arrayOf("subscriber_id"))
	val subscriberId: String? = null
)