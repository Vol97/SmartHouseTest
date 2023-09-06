package com.serhiivoloshyn.smarthousetest.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "devices")
data class Device(
    @SerializedName("PK_Device")
    @PrimaryKey(autoGenerate = false)
    val pkDevice: String,
    @SerializedName("MacAddress")
    val macAddress: String,
    @SerializedName("PK_DeviceType")
    val pkDeviceType: Int,
    @SerializedName("PK_DeviceSubType")
    val pkDeviceSubType: Int,
    @SerializedName("Firmware")
    val firmware: String,
    @SerializedName("Server_Device")
    val serverDevice: String,
    @SerializedName("Server_Event")
    val serverEvent: String,
    @SerializedName("Server_Account")
    val serverAccount: String,
    @SerializedName("InternalIP")
    val internalIP: String,
    @SerializedName("LastAliveReported")
    val lastAliveReported: String,
    @SerializedName("Platform")
    val platform: Platform,
    var name: String? = "House"
): Parcelable
