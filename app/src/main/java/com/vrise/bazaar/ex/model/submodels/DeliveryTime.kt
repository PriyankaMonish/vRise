package com.vrise.bazaar.ex.model.submodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DeliveryTime {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("slot_name")
    @Expose
    var slotName: String? = null

    @SerializedName("spot_delivery")
    @Expose
    var spotDelivery: String? = null

    @SerializedName("slot_description")
    @Expose
    var slotDescription: String? = null

    @SerializedName("start_time")
    @Expose
    var startTime: String? = null

    @SerializedName("end_time")
    @Expose
    var endTime: String? = null

    @SerializedName("charge")
    @Expose
    var charge: String? = null

    @SerializedName("sub_charge")
    @Expose
    var subCharge: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("added_by")
    @Expose
    var addedBy: String? = null
}