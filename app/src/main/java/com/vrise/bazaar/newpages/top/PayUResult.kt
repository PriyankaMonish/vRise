package com.vrise.bazaar.newpages.top

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class PayUResult(

	@field:SerializedName("country")
	@field:JsonField(name = arrayOf("country"))
	val country: String? = null,

	@field:SerializedName("id")
	@field:JsonField(name = arrayOf("id"))
	val id: String? = null,

	@field:SerializedName("cardCategory")
	@field:JsonField(name = arrayOf("cardCategory"))
	val cardCategory: String? = null,

	@field:SerializedName("discount")
	@field:JsonField(name = arrayOf("discount"))
	val discount: String? = null,

	@field:SerializedName("udf10")
	@field:JsonField(name = arrayOf("udf10"))
	val udf10: String? = null,

	@field:SerializedName("device_type")
	@field:JsonField(name = arrayOf("device_type"))
	val deviceType: String? = null,

	@field:SerializedName("mode")
	@field:JsonField(name = arrayOf("mode"))
	val mode: String? = null,

	@field:SerializedName("cardhash")
	@field:JsonField(name = arrayOf("cardhash"))
	val cardhash: String? = null,

	@field:SerializedName("error_Message")
	@field:JsonField(name = arrayOf("error_Message", "Error_Message"))
	val errorMessage: String? = null,

	@field:SerializedName("state")
	@field:JsonField(name = arrayOf("state"))
	val state: String? = null,

	@field:SerializedName("payment_source")
	@field:JsonField(name = arrayOf("payment_source"))
	val paymentSource: String? = null,

	@field:SerializedName("bankcode")
	@field:JsonField(name = arrayOf("bankcode"))
	val bankcode: String? = null,

	@field:SerializedName("txnid")
	@field:JsonField(name = arrayOf("txnid"))
	val txnid: String? = null,

	@field:SerializedName("net_amount_debit")
	@field:JsonField(name = arrayOf("net_amount_debit"))
	val netAmountDebit: String? = null,

	@field:SerializedName("transaction_fee")
	@field:JsonField(name = arrayOf("transaction_fee"))
	val transaction_fee: String? = null,

	@field:SerializedName("lastname")
	@field:JsonField(name = arrayOf("lastname"))
	val lastname: String? = null,

	@field:SerializedName("zipcode")
	@field:JsonField(name = arrayOf("zipcode"))
	val zipcode: String? = null,

	@field:SerializedName("phone")
	@field:JsonField(name = arrayOf("phone"))
	val phone: String? = null,

	@field:SerializedName("productinfo")
	@field:JsonField(name = arrayOf("productinfo"))
	val productinfo: String? = null,

	@field:SerializedName("hash")
	@field:JsonField(name = arrayOf("hash"))
	val hash: String? = null,

	@field:SerializedName("status")
	@field:JsonField(name = arrayOf("status"))
	val status: String? = null,

	@field:SerializedName("firstname")
	@field:JsonField(name = arrayOf("firstname"))
	val firstname: String? = null,

	@field:SerializedName("city")
	@field:JsonField(name = arrayOf("city"))
	val city: String? = null,

	@field:SerializedName("error")
	@field:JsonField(name = arrayOf("error"))
	val error: String? = null,

	@field:SerializedName("addedon")
	@field:JsonField(name = arrayOf("addedon"))
	val addedon: String? = null,

	@field:SerializedName("udf9")
	@field:JsonField(name = arrayOf("udf9"))
	val udf9: String? = null,

	@field:SerializedName("udf7")
	@field:JsonField(name = arrayOf("udf7"))
	val udf7: String? = null,

	@field:SerializedName("udf8")
	@field:JsonField(name = arrayOf("udf8"))
	val udf8: String? = null,

	@field:SerializedName("issuing_bank")
	@field:JsonField(name = arrayOf("issuing_bank"))
	val issuingBank: String? = null,

	@field:SerializedName("bank_ref_num")
	@field:JsonField(name = arrayOf("bank_ref_num"))
	val bankRefNum: String? = null,

	@field:SerializedName("key")
	@field:JsonField(name = arrayOf("key"))
	val key: String? = null,

	@field:SerializedName("email")
	@field:JsonField(name = arrayOf("email"))
	val email: String? = null,

	@field:SerializedName("amount")
	@field:JsonField(name = arrayOf("amount"))
	val amount: String? = null,

	@field:SerializedName("unmappedstatus")
	@field:JsonField(name = arrayOf("unmappedstatus"))
	val unmappedstatus: String? = null,

	@field:SerializedName("address2")
	@field:JsonField(name = arrayOf("address2"))
	val address2: String? = null,

	@field:SerializedName("address1")
	@field:JsonField(name = arrayOf("address1"))
	val address1: String? = null,

	@field:SerializedName("udf5")
	@field:JsonField(name = arrayOf("udf5"))
	val udf5: String? = null,

	@field:SerializedName("mihpayid")
	@field:JsonField(name = arrayOf("mihpayid"))
	val mihpayid: String? = null,

	@field:SerializedName("udf6")
	@field:JsonField(name = arrayOf("udf6"))
	val udf6: String? = null,

	@field:SerializedName("udf3")
	@field:JsonField(name = arrayOf("udf3"))
	val udf3: String? = null,

	@field:SerializedName("udf4")
	@field:JsonField(name = arrayOf("udf4"))
	val udf4: String? = null,

	@field:SerializedName("udf1")
	@field:JsonField(name = arrayOf("udf1"))
	val udf1: String? = null,

	@field:SerializedName("card_type")
	@field:JsonField(name = arrayOf("card_type"))
	val cardType: String? = null,

	@field:SerializedName("udf2")
	@field:JsonField(name = arrayOf("udf2"))
	val udf2: String? = null,

	@field:SerializedName("field1")
	@field:JsonField(name = arrayOf("field1"))
	val field1: String? = null,

	@field:SerializedName("cardnum")
	@field:JsonField(name = arrayOf("cardnum"))
	val cardnum: String? = null,

	@field:SerializedName("field7")
	@field:JsonField(name = arrayOf("field7"))
	val field7: String? = null,

	@field:SerializedName("field6")
	@field:JsonField(name = arrayOf("field6"))
	val field6: String? = null,

	@field:SerializedName("field9")
	@field:JsonField(name = arrayOf("field9"))
	val field9: String? = null,

	@field:SerializedName("field8")
	@field:JsonField(name = arrayOf("field8"))
	val field8: String? = null,

	@field:SerializedName("field3")
	@field:JsonField(name = arrayOf("field3"))
	val field3: String? = null,

	@field:SerializedName("field2")
	@field:JsonField(name = arrayOf("field2"))
	val field2: String? = null,

	@field:SerializedName("field5")
	@field:JsonField(name = arrayOf("field5"))
	val field5: String? = null,

	@field:SerializedName("bank_ref_no")
	@field:JsonField(name = arrayOf("bank_ref_no"))
	val bank_ref_no: String? = null,

	@field:SerializedName("ibibo_code")
	@field:JsonField(name = arrayOf("ibibo_code"))
	val ibibo_code: String? = null,

	@field:SerializedName("error_code")
	@field:JsonField(name = arrayOf("error_code"))
	val error_code: String? = null,

	@field:SerializedName("is_seamless")
	@field:JsonField(name = arrayOf("is_seamless"))
	val is_seamless: String? = null,

	@field:SerializedName("surl")
	@field:JsonField(name = arrayOf("surl"))
	val surl: String? = null,

	@field:SerializedName("furl")
	@field:JsonField(name = arrayOf("furl"))
	val furl: String? = null,

	@field:SerializedName("PG_TYPE")
	@field:JsonField(name = arrayOf("PG_TYPE"))
	val pGTYPE: String? = null,

	@field:SerializedName("card_no")
	@field:JsonField(name = arrayOf("card_no"))
	val card_no: String? = null,

	@field:SerializedName("field4")
	@field:JsonField(name = arrayOf("field4"))
	val field4: String? = null,

	@field:SerializedName("name_on_card")
	@field:JsonField(name = arrayOf("name_on_card"))
	val nameOnCard: String? = null
)