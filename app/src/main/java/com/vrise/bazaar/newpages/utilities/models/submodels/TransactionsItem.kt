package com.vrise.bazaar.newpages.utilities.models.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class TransactionsItem(

	@field:SerializedName("date")
	@field:JsonField(name = arrayOf("date"))
	val date: String? = null,

	@field:SerializedName("amount")
	@field:JsonField(name = arrayOf("amount"))
	val amount: String? = null,

	@field:SerializedName("orderId")
	@field:JsonField(name = arrayOf("orderId"))
	val orderId: String? = null,

	@field:SerializedName("addedBy")
	@field:JsonField(name = arrayOf("addedBy"))
	val addedBy: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("time")
	@field:JsonField(name = arrayOf("time"))
	val time: String? = null,

	@field:SerializedName("title")
	@field:JsonField(name = arrayOf("title"))
	val title: String? = null,

	@field:SerializedName("type")
	@field:JsonField(name = arrayOf("type"))
	val type: String? = null,

	@field:SerializedName("addedById")
	@field:JsonField(name = arrayOf("addedById"))
	val addedById: String? = null
)