package com.serhiivoloshyn.smarthousetest

import com.serhiivoloshyn.smarthousetest.api.SmartHouseApi
import com.serhiivoloshyn.smarthousetest.data.Device
import com.serhiivoloshyn.smarthousetest.dataBase.DevicesDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartHouseRepository @Inject constructor(
    private val api: SmartHouseApi,
    database: DevicesDatabase
) {
    private val devicesDao = database.devicesDao()

    suspend fun refreshDevices(): List<Device> {
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

        if (response != null && !devices.isNullOrEmpty()) {
            var number = 1

            devices.forEach {
                it.name = "House $number"
                number += 1
            }

            saveDevices(devices)
        }

        return devicesDao.getAll()
    }

    private fun saveDevices(devicesList: List<Device>) {
        devicesList.forEach {
            devicesDao.insertAll(it)
        }
    }

    fun getSavedDevices(): List<Device> {
        return devicesDao.getAll()
    }

    fun deleteDevice(device: Device) {
        devicesDao.delete(device)
    }

    fun getDeviceByName(name: String): Device {
        return devicesDao.getDeviceByName(name)
    }

    fun updateDevices(device: Device) {
        devicesDao.updateAll(device)
    }
}