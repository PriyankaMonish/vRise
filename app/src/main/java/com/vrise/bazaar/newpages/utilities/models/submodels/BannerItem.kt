package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class BannerItem(

	@field:SerializedName("image")
	@field:JsonField(name = arrayOf("image"))
	val image: String? = null,

	@field:SerializedName("link")
	@field:JsonField(name = arrayOf("link"))
	val link: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null
)