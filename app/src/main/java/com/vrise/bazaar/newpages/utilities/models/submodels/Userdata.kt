package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Userdata(

	@field:SerializedName("image")
	@field:JsonField(name = arrayOf("image"))
	val image: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("mobile")
	@field:JsonField(name = arrayOf("mobile"))
	val mobile: String? = null,

	@field:SerializedName("email")
	@field:JsonField(name = arrayOf("email"))
	val email: String? = null
)