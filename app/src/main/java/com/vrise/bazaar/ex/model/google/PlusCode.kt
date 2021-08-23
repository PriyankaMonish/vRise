package com.vrise.bazaar.ex.model.google

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class PlusCode(

	@field:SerializedName("compound_code")
	@field:JsonField(name = arrayOf("compound_code"))
	val compoundCode: String? = null,

	@field:SerializedName("global_code")
	@field:JsonField(name = arrayOf("global_code"))
	val globalCode: String? = null
)