package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Profiledata(

        @field:SerializedName("pincode")
        @field:JsonField(name = arrayOf("pincode"))
        val pincode: String? = null,

        @field:SerializedName("code")
        @field:JsonField(name = arrayOf("code"))
        val code: String? = null,

        @field:SerializedName("address")
        @field:JsonField(name = arrayOf("address"))
        val address: String? = null,

        @field:SerializedName("post_office")
        @field:JsonField(name = arrayOf("post_office"))
        val post_office: String? = null,

        @field:SerializedName("district")
        @field:JsonField(name = arrayOf("district"))
        val district: String? = null,

        @field:SerializedName("district_name")
        @field:JsonField(name = arrayOf("district_name"))
        val district_name: String? = null,

        @field:SerializedName("img_link")
        @field:JsonField(name = arrayOf("img_link"))
        val imgLink: String? = null,

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("agent_code")
        @field:JsonField(name = arrayOf("agent_code"))
        val agent_code: String? = null,

        @field:SerializedName("mobile")
        @field:JsonField(name = arrayOf("mobile"))
        val mobile: String? = null,

        @field:SerializedName("email")
        @field:JsonField(name = arrayOf("email"))
        val email: String? = null,

        @field:SerializedName("agent_id")
        @field:JsonField(name = arrayOf("agent_id"))
        val agentId: String? = null,

        @field:SerializedName("address2")
        @field:JsonField(name = arrayOf("address2"))
        val address2: String? = null,

        @field:SerializedName("city")
        @field:JsonField(name = arrayOf("city"))
        val city: String? = null,

        @field:SerializedName("city_name")
        @field:JsonField(name = arrayOf("city_name"))
        val cityName: String? = null,

        @field:SerializedName("state_name")
        @field:JsonField(name = arrayOf("state_name"))
        val stateName: String? = null,

        @field:SerializedName("state")
        @field:JsonField(name = arrayOf("state"))
        val state: String? = null
)