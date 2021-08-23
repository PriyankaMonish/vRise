package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class SubduelistItem(

	@field:SerializedName("user_type")
	@field:JsonField(name = arrayOf("user_type"))
	val userType: String? = null,

	@field:SerializedName("code")
	@field:JsonField(name = arrayOf("code"))
	val code: String? = null,

	@field:SerializedName("route_id")
	@field:JsonField(name = arrayOf("route_id"))
	val routeId: String? = null,

	@field:SerializedName("img_link")
	@field:JsonField(name = arrayOf("img_link"))
	val imgLink: String? = null,

	@field:SerializedName("due")
	@field:JsonField(name = arrayOf("due"))
	val due: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("mobile")
	@field:JsonField(name = arrayOf("mobile"))
	val mobile: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)