package com.vrise.bazaar.ex.shop.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vrise.bazaar.ex.model.TrackData
import com.vrise.bazaar.ex.model.mainmodels.Request
import com.vrise.bazaar.ex.model.submodels.Data
import com.vrise.bazaar.ex.shop.dataprovider.RepoLive

class TrackPageViewModel(private val repos: RepoLive?) : ViewModel() {
	fun trackorder(nothing: Request) : LiveData<TrackData?>? = trackorder1(nothing)

	private fun trackorder1(nothing: Request): LiveData<TrackData?>? {
		return repos?.trackorder(nothing)
	}

	fun dboylocation(nothing: Request) : LiveData<Data?>? = dboylocation1(nothing)

	private fun dboylocation1(nothing: Request): LiveData<Data?>? = repos?.dboylocation(nothing)

	/*suspend fun getDirections(origin: String, destination: String, key: String, directions: TrackPageFragment.GoogleService?) {
		viewModelScope.launch {
			try {
				val rep = withContext(Dispatchers.IO) {
					repos?.refreshTitle(origin, destination, key, directions)
				}
				directionsResult?.value = rep?.value
			} catch (e: Exception) {
				e.printStackTrace()
			}
		}
	}*/

	fun getDirection(origin: String, destination: String, key: String, directions: TrackPageFragment.GoogleService?) : LiveData<com.vrise.bazaar.ex.model.route.DirectionsResult?>? = repos?.refreshTitle(origin, destination, key, directions)

}
