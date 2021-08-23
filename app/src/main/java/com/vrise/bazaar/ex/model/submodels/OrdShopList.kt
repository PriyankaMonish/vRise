package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName
import com.vrise.bazaar.ex.model.DeliveryboyData
import com.vrise.bazaar.ex.model.TrackData

class OrdShopList {
    @field:SerializedName("shop_id")
    @field:JsonField(name = arrayOf("track_data"))
    val shop_id: String? = null

    @field:SerializedName("shop_name")
    @field:JsonField(name = arrayOf("shop_name"))
    val shop_name: String? = null
}
