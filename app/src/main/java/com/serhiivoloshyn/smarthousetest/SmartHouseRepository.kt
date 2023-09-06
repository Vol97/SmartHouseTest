package com.serhiivoloshyn.smarthousetest

import com.serhiivoloshyn.smarthousetest.api.SmartHouseApi
import com.serhiivoloshyn.smarthousetest.data.Device
import com.serhiivoloshyn.smarthousetest.dataBase.DevicesDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartHouseRepository @Inject constructor(
    private val api: SmartHouseApi,
    private val database: DevicesDatabase
) {
    private val devicesDao = database.devicesDao()

    suspend fun getDevices(): List<Device>? {
        val devices: List<Device>?

        val response = try {
            api.getDevices()
        } catch (exception: Exception) {
            null
        }

        devices = if (response?.isSuccessful == true) {
            response.body()?.devices
        } else {
            emptyList()
        }

        return devices
    }

    fun getSavedDevices(): List<Device> {
        return devicesDao.getAll()
    }
}