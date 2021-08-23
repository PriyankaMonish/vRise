package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Bank(

	@field:SerializedName("ifsc_code")
	@field:JsonField(name = arrayOf("ifsc_code"))
	val ifscCode: String? = null,

	@field:SerializedName("account_number")
	@field:JsonField(name = arrayOf("account_number"))
	val accountNumber: String? = null,

	@field:SerializedName("agent_id")
	@field:JsonField(name = arrayOf("agent_id"))
	val agentId: String? = null,

	@field:SerializedName("account_holder")
	@field:JsonField(name = arrayOf("account_holder"))
	val accountHolder: String? = null,

	@field:SerializedName("branch_name")
	@field:JsonField(name = arrayOf("branch_name"))
	val branchName: String? = null,

	@field:SerializedName("last_update")
	@field:JsonField(name = arrayOf("last_update"))
	val lastUpdate: String? = null,

	@field:SerializedName("bank_name")
	@field:JsonField(name = arrayOf("bank_name"))
	val bankName: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null
)