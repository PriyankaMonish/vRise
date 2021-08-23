package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class BannerItem(

	@field:SerializedName("image")
	@field:JsonField(name = arrayOf("image"))
	var image: String? = null,

	@field:SerializedName("link")
	@field:JsonField(name = arrayOf("link"))
	val link: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null
)