package com.vrise.bazaar.ex.model.route

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

@JsonObject
data class Polyline(

	@field:SerializedName("points")
	@field:JsonField(name = arrayOf("points"))
	val points: String? = null
) {
	fun decodePath(encoded : String): MutableList<LatLng> {
		val poly: MutableList<LatLng> = ArrayList()
		var index = 0
		val len: Int = encoded.length
		var lat = 0
		var lng = 0

		while (index < len) {
			var b: Int
			var shift = 0
			var result = 0
			do {
				b = encoded.get(index++).toInt() - 63
				result = result or (b and 0x1f) shl shift
				shift += 5
			} while (b >= 0x20)
			val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
			lat += dlat
			shift = 0
			result = 0
			do {
				b = encoded.get(index++).toInt() - 63
				result = result or (b and 0x1f) shl shift
				shift += 5
			} while (b >= 0x20)
			val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
			lng += dlng
			val p =
				LatLng(
					lat.toDouble() / 1E5,
					lng.toDouble() / 1E5
				)
			poly.add(p)
		}
		return poly
	}
}