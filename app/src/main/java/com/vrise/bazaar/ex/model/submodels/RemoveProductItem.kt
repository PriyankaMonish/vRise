package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class RemoveProductItem(

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null
)