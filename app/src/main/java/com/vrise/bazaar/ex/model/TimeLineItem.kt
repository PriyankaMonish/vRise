package com.vrise.bazaar.ex.model

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class TimeLineItem(

	@field:SerializedName("status_title")
	@field:JsonField(name = arrayOf("status_title"))
	val statusTitle: String? = null,

	@field:SerializedName("status_message")
	@field:JsonField(name = arrayOf("status_message"))
	val statusMessage: String? = null,

	@field:SerializedName("date_time")
	@field:JsonField(name = arrayOf("date_time"))
	val dateTime: String? = null,

	@field:SerializedName("notifi_type")
	@field:JsonField(name = arrayOf("notifi_type"))
	val notifiType: String? = null,

	@field:SerializedName("type_id")
	@field:JsonField(name = arrayOf("type_id"))
	val typeId: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)