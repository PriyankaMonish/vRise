package com.vrise.bazaar.newpages.utilities.models.submodels

import com.google.gson.annotations.SerializedName
import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class Subdata(

        @field:SerializedName("pincode")
        @field:JsonField(name = arrayOf("pincode"))
        val pincode: String? = null,

        @field:SerializedName("subscription_request")
        @field:JsonField(name = arrayOf("subscription_request"))
        val subscriptionRequest: ArrayList<SubscriptionRequestItem?>? = null,

        @field:SerializedName("subscriber_due")
        @field:JsonField(name = arrayOf("subscriber_due"))
        val subscriberDue: String? = null,

        @field:SerializedName("address2")
        @field:JsonField(name = arrayOf("address2"))
        var address2: String? = null,

        @field:SerializedName("state")
        @field:JsonField(name = arrayOf("state"))
        var state: String? = null,

        @field:SerializedName("district_name")
        @field:JsonField(name = arrayOf("district_name"))
        var district_name: String? = null,

        @field:SerializedName("state_name")
        @field:JsonField(name = arrayOf("state_name"))
        var state_name: String? = null,

        @field:SerializedName("post_office")
        @field:JsonField(name = arrayOf("post_office"))
        var post_office: String? = null,

        @field:SerializedName("district")
        @field:JsonField(name = arrayOf("district"))
        var district: String? = null,

        @field:SerializedName("subscriptions")
        @field:JsonField(name = arrayOf("subscriptions"))
        var subscriptions: ArrayList<SubscriptionsItem>? = null,

/*

        @field:SerializedName("subscriptions")
        @field:JsonField(name = arrayOf("subscriptions"))
        var subscriptions: ArrayList<Subscribeditem>? = null,

*/
        @field:SerializedName("code")
        @field:JsonField(name = arrayOf("code"))
        val code: String? = null,

        @field:SerializedName("route_id")
        @field:JsonField(name = arrayOf("route_id"))
        val routeId: String? = null,

        @field:SerializedName("address")
        @field:JsonField(name = arrayOf("address"))
        val address: String? = null,

        @field:SerializedName("signup_dates")
        @field:JsonField(name = arrayOf("signup_dates"))
        val signupDates: String? = null,

        @field:SerializedName("agent_id")
        @field:JsonField(name = arrayOf("agent_id"))
        val agentId: String? = null,

        @field:SerializedName("img_link")
        @field:JsonField(name = arrayOf("img_link"))
        val imgLink: String? = null,

        @field:SerializedName("mobile")
        @field:JsonField(name = arrayOf("mobile"))
        val mobile: String? = null,

        @field:SerializedName("name")
        @field:JsonField(name = arrayOf("name"))
        val name: String? = null,

        @field:SerializedName("id")
        @field:JsonField(name = arrayOf("id"))
        val id: String? = null,

        @field:SerializedName("device_id")
        @field:JsonField(name = arrayOf("device_id"))
        val deviceae: String? = null,

        /* @field:SerializedName("subscription_request")
         @field:JsonField(name = arrayOf("subscription_request"))
         val subscriptionRequest: List<com.ibrbazaar.bazaar.newpages.agency.subscriber.subscriberdetails.SubscriptionRequestItem?>? = null,
        */
        @field:SerializedName("email")
        @field:JsonField(name = arrayOf("email"))
        val email: String? = null,

        @field:SerializedName("status")
        @field:JsonField(name = arrayOf("status"))
        val status: String? = null,

        @field:SerializedName("route_name")
        @field:JsonField(name = arrayOf("route_name"))
        val route_name: String? = null
)