package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class NotificationsItem(

	@field:SerializedName("user_id")
	@field:JsonField(name = arrayOf("user_id"))
	val userId: String? = null,

	@field:SerializedName("type_id")
	@field:JsonField(name = arrayOf("type_id"))
	val typeId: String? = null,

	@field:SerializedName("notify_on")
	@field:JsonField(name = arrayOf("notify_on"))
	val notifyOn: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("type")
	@field:JsonField(name = arrayOf("type"))
	val type: String? = null,

	@field:SerializedName("title")
	@field:JsonField(name = arrayOf("title"))
	val title: String? = null,

	@field:SerializedName("message")
	@field:JsonField(name = arrayOf("message"))
	val message: String? = null,

	@field:SerializedName("notify_to")
	@field:JsonField(name = arrayOf("notify_to"))
	val notifyTo: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
    var status: String? = null
)