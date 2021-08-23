package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class DistrictsItem(

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("state_id")
	@field:JsonField(name = arrayOf("state_id"))
	val stateId: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)