package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class AutobillData(

        @field:SerializedName("date")
        @field:JsonField(name = arrayOf("date"))
        val date: String? = null,

        @field:SerializedName("agent_id")
        @field:JsonField(name = arrayOf("agent_id"))
        val agentId: String? = null,

        @field:SerializedName("last_update")
        @field:JsonField(name = arrayOf("last_update"))
        val lastUpdate: String? = null,

        @field:SerializedName("bill_type")
        @field:JsonField(name = arrayOf("bill_type"))
        val billType: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("sms_users")
        @field:JsonField(name = arrayOf("sms_users"))
        val smsUsers: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        val status: String? = null
)