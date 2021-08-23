package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class ReceiptListItem(

	@field:SerializedName("subscriber_id")
	@field:JsonField(name = arrayOf("subscriber_id"))
	val subscriberId: String? = null,

	@field:SerializedName("agent_id")
	@field:JsonField(name = arrayOf("agent_id"))
	val agentId: String? = null,

	@field:SerializedName("due_amount")
	@field:JsonField(name = arrayOf("due_amount"))
	val dueAmount: String? = null,

	@field:SerializedName("bill_date")
	@field:JsonField(name = arrayOf("bill_date"))
	val billDate: String? = null,

	@field:SerializedName("total_amount")
	@field:JsonField(name = arrayOf("total_amount"))
	val totalAmount: String? = null,

	@field:SerializedName("bill_link")
	@field:JsonField(name = arrayOf("bill_link"))
	val billLink: String? = null,

	@field:SerializedName("bill_code")
	@field:JsonField(name = arrayOf("bill_code"))
	val billCode: String? = null,

	@field:SerializedName("bill_month")
	@field:JsonField(name = arrayOf("bill_month"))
	val billMonth: String? = null,

	@field:SerializedName("payment_status")
	@field:JsonField(name = arrayOf("payment_status"))
	val paymentStatus: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)