package com.vrise.bazaar.ex.model.newmodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class CustomerCare(

	@field:SerializedName("1")
	@field:JsonField(name = arrayOf("1"))
	val jsonMember1: String? = null,

	@field:SerializedName("2")
	@field:JsonField(name = arrayOf("2"))
	val jsonMember2: String? = null
)