package com.serhiivoloshyn.smarthousetest.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.serhiivoloshyn.smarthousetest.data.Device
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeviceListResponse(
    @SerializedName("Devices")
    val devices: List<Device>
): Parcelable
