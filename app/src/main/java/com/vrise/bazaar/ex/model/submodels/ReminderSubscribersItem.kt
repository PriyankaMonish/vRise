package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class ReminderSubscribersItem(

        @field:SerializedName("pincode")
        @field:JsonField(name = arrayOf("pincode"))
        val pincode: String? = null,

        @field:SerializedName("subscriptions")
        @field:JsonField(name = arrayOf("subscriptions"))
        val subscriptions: String? = null,

        @field:SerializedName("code")
        @field:JsonField(name = arrayOf("code"))
        val code: String? = null,

        @field:SerializedName("route_id")
        @field:JsonField(name = arrayOf("route_id"))
        val routeId: String? = null,

        @field:SerializedName("address")
        @field:JsonField(name = arrayOf("address"))
        val address: String? = null,

        @field:SerializedName("address2")
        @field:JsonField(name = arrayOf("address2"))
        val address2: String? = null,

        @field:SerializedName("city")
        @field:JsonField(name = arrayOf("city"))
        val city: String? = null,

        @field:SerializedName("img_link")
        @field:JsonField(name = arrayOf("img_link"))
        val imgLink: String? = null,

        @field:SerializedName("mobile")
        @field:JsonField(name = arrayOf("mobile"))
        val mobile: String? = null,

        @field:SerializedName("signup_date")
        @field:JsonField(name = arrayOf("signup_date"))
        val signupDate: String? = null,

        @field:SerializedName("city_name")
        @field:JsonField(name = arrayOf("city_name"))
        val cityName: String? = null,

        @field:SerializedName("user_type")
        @field:JsonField(name = arrayOf("user_type"))
        val userType: String? = null,

        @field:SerializedName("state_name")
        @field:JsonField(name = arrayOf("state_name"))
        val stateName: String? = null,

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("new_unscubscribe")
        @field:JsonField(name = arrayOf("state"))
        val newUnsubscription: String? = null,

        @field:SerializedName("new_subscription")
        @field:JsonField(name = arrayOf("state"))
        val newSubscription: String? = null,

        @field:SerializedName("state")
        @field:JsonField(name = arrayOf("state"))
        val state: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        val status: String? = null
)