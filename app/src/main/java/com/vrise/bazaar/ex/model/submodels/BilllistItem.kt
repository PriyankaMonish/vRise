package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@JsonObject
data class BilllistItem(

	@field:SerializedName("additional_amount")
	@field:JsonField(name = arrayOf("additional_amount"))
	val additionalAmount: String? = null,

	@field:SerializedName("agent_id")
	@field:JsonField(name = arrayOf("agent_id"))
	val agentId: String? = null,

	@field:SerializedName("agent_name")
	@field:JsonField(name = arrayOf("agent_name"))
	val agentName: String? = null,

	@field:SerializedName("bill_date")
	@field:JsonField(name = arrayOf("bill_date"))
	val billDate: String? = null,

	@field:SerializedName("bill_code")
	@field:JsonField(name = arrayOf("bill_code"))
	val billCode: String? = null,

	@field:SerializedName("discount_amount")
	@field:JsonField(name = arrayOf("discount_amount"))
	val discountAmount: String? = null,

	@field:SerializedName("payed_amount")
	@field:JsonField(name = arrayOf("payed_amount"))
	val payedAmount: String? = null,

	@field:SerializedName("payment_status")
	@field:JsonField(name = arrayOf("payment_status"))
	val paymentStatus: String? = null,

	@field:SerializedName("remark")
	@field:JsonField(name = arrayOf("remark"))
	val remark: String? = null,

	@field:SerializedName("bill_products")
	@field:JsonField(name = arrayOf("bill_products"))
	val billProducts: List<BillProductsItem?>? = null,

	@field:SerializedName("subscriber_id")
	@field:JsonField(name = arrayOf("subscriber_id"))
	val subscriberId: String? = null,

	@field:SerializedName("due_amount")
	@field:JsonField(name = arrayOf("due_amount"))
	val dueAmount: String? = null,

	@field:SerializedName("total_amount")
	@field:JsonField(name = arrayOf("total_amount"))
	val totalAmount: String? = null,

	@field:SerializedName("bill_month")
	@field:JsonField(name = arrayOf("bill_month"))
	val billMonth: String? = null,

	@field:SerializedName("last_update")
	@field:JsonField(name = arrayOf("last_update"))
	val lastUpdate: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("subscriber_name")
	@field:JsonField(name = arrayOf("subscriber_name"))
	val subscriberName: String? = null,

	@field:SerializedName("product_amount")
	@field:JsonField(name = arrayOf("product_amount"))
	val productAmount: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
) : Serializable