package com.vrise.bazaar.newpages.top.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrise.bazaar.newpages.top.TopRepository
import com.vrise.bazaar.newpages.top.sliderui.model.Slide
import com.vrise.bazaar.newpages.utilities.models.mainmodels.Request


class TopViewModel(private val topRepository: TopRepository?) : ViewModel() {

    fun newsGet(utocken: String?) = topRepository?.newsGet(utocken)

    fun shopDashboard(utocken: String) = topRepository?.getShoppingDashboard(utocken)

    fun setBanner(stringArrayList: java.util.ArrayList<String>?): MutableLiveData<ArrayList<Slide>> {
        val slideList = ArrayList<Slide>()
        val liveList = MutableLiveData<ArrayList<Slide>>()
        if (stringArrayList != null && stringArrayList.isNotEmpty()){
            for (i in 0 until stringArrayList.size){
                slideList.add(
                    Slide(
                        i,
                        stringArrayList[i]
                    )
                )
            }
        }
        liveList.value = slideList
        return liveList
    }

    fun addAddress(request: Request)  = topRepository?.addAddress(request)

    fun getBanner(request: Request) = topRepository?.getBanner(request)
}