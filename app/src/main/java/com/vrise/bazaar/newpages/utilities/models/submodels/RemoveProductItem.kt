package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class RemoveProductItem(

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null
)