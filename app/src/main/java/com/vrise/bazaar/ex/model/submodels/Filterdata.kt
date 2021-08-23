package com.vrise.bazaar.ex.model.submodels

import com.bluelinelabs.logansquare.annotation.JsonField
import com.google.gson.annotations.SerializedName

class Filterdata (

    @field:SerializedName("type")
    @field:JsonField(name = arrayOf("type"))
    var type: String?=null,

    @field:SerializedName("type_label")
    @field:JsonField(name = arrayOf("type_label"))
    var type_label:String?=null,

    @field:SerializedName("values")
    @field:JsonField(name = arrayOf("values"))
    val values: List<Valuess>? = null,


    var isChecked:Boolean = false
    )

{

    fun getSelectedValuesAsString():String{
        var selectedValues = ""

        for(value in values!!.iterator()){
            if (value.isSelected){
                selectedValues += "${value.id},"
            }
        }

        if (selectedValues != "") {
            selectedValues = selectedValues.substring(0, selectedValues.lastIndexOf(","))
        }
        return selectedValues
    }
}