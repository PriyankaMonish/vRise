package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.gson.annotations.SerializedName

@JsonObject
data class AgentData(

        @field:SerializedName("pincode")
        @field:JsonField(name = arrayOf("pincode"))
        val pincode: String? = null,

        @field:SerializedName("code")
        @field:JsonField(name = arrayOf("code"))
        val code: String? = null,

        @field:SerializedName("address")
        @field:JsonField(name = arrayOf("address"))
        val address: String? = null,

        @field:SerializedName("address2")
        @field:JsonField(name = arrayOf("address2"))
        val address2: String? = "",

        @field:SerializedName("district_name")
        @field:JsonField(name = arrayOf("district_name"))
        val district_name: String? = "",

        @field:SerializedName("district")
        @field:JsonField(name = arrayOf("district"))
        val district: String? = null,

        @field:SerializedName("state")
        @field:JsonField(name = arrayOf("state"))
        val state: String? = null,

        @field:SerializedName("state_name")
        @field:JsonField(name = arrayOf("state_name"))
        val states: String? = "",

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("mobile")
        @field:JsonField(name = arrayOf("mobile"))
        val mobile: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("email")
        @field:JsonField(name = arrayOf("email"))
        val email: String? = null
)