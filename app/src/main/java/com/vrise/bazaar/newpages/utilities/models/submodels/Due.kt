package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Due(

	@field:SerializedName("due")
	@field:JsonField(name = arrayOf("due"))
	val due: String? = null,

	@field:SerializedName("subscriber_id")
	@field:JsonField(name = arrayOf("subscriber_id"))
	val subscriberId: String? = null
)