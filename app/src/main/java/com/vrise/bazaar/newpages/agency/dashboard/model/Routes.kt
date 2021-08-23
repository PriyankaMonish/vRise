package com.vrise.bazaar.newpages.agency.dashboard.model

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.newpages.utilities.models.submodels.RootlistItem

data class Routes(

        @field:SerializedName("due")
        @field:JsonField(name = arrayOf("due"))
        val due: String? = null,

        @field:SerializedName("device_id")
        @field:JsonField(name = arrayOf("device_id"))
        val list: ArrayList<RootlistItem>? = null,

        @field:SerializedName("notify_count")
        @field:JsonField(name = arrayOf("notify_count"))
        val notificationCount: String = "0"

)