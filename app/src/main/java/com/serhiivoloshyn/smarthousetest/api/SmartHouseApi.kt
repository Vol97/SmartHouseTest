package com.serhiivoloshyn.smarthousetest.api

import retrofit2.Response
import retrofit2.http.GET

interface SmartHouseApi {

    @GET("test_android/items.test")
    suspend fun getDevices(): Response<DeviceListResponse>
}