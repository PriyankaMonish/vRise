package com.vrise.bazaar.ex.model.temp

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class Wallet(

	@field:SerializedName("income")
	@field:JsonField(name = arrayOf("income"))
	val income: String? = null,

	@field:SerializedName("balance")
	@field:JsonField(name = arrayOf("balance"))
	val balance: String? = null,

	@field:SerializedName("transactions")
	@field:JsonField(name = arrayOf("transactions"))
	val transactions: List<TransactionsItem?>? = null,

	@field:SerializedName("expenses")
	@field:JsonField(name = arrayOf("expenses"))
	val expenses: String? = null
)