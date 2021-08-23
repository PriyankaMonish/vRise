package com.vrise.bazaar.newpages.retrofit

import com.vrise.bazaar.ex.model.google.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface DownloadService {
	@Streaming
	@GET
	fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
}

interface NewGoogleService {
	@GET("json")
	fun getAddressList(
		@Query("latlng") lats: String, @Query("language") language: String, @Query("sensor") sensor: String, @Query("key") key: String): Call<Response>
}