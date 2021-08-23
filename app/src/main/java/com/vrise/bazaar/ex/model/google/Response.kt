package com.vrise.bazaar.ex.model.google

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Response(

	@field:SerializedName("plus_code")
	@field:JsonField(name = arrayOf("plus_code"))
	val plusCode: PlusCode? = null,

	@field:SerializedName("results")
	@field:JsonField(name = arrayOf("results"))
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("error_message")
	@field:JsonField(name = arrayOf("error_message"))
	val error_message: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)