package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class ReminderRoutesItem(

	@field:SerializedName("area")
	@field:JsonField(name = arrayOf("area"))
	val area: String? = null,

	@field:SerializedName("newaddd")
	@field:JsonField(name = arrayOf("newaddd"))
	val newaddd: String? = "0",

	@field:SerializedName("agent_id")
	@field:JsonField(name = arrayOf("agent_id"))
	val agentId: String? = null,

	@field:SerializedName("name")
	@field:JsonField(name = arrayOf("name"))
	val name: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)