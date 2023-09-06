package com.serhiivoloshyn.smarthousetest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiivoloshyn.smarthousetest.data.Device
import com.serhiivoloshyn.smarthousetest.data.DeviceListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmartHouseViewModel @Inject constructor(
    private val repository: SmartHouseRepository
) : ViewModel() {
    private val _deviceListState = MutableStateFlow(
        DeviceListState(emptyList(), false)
    )
    val deviceListState: StateFlow<DeviceListState> = _deviceListState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _deviceListState.update {
                it.copy(
                    devicesList = repository.getSavedDevices().sortedBy { device ->
                        device.pkDevice.toInt()
                    }
                )
            }
        }
    }

    fun refreshDevicesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val devicesListDeferred: Deferred<List<Device>> = async {
                repository.refreshDevices()
            }

            _deviceListState.update {
                it.copy(isRefreshing = true)
            }

            _deviceListState.update {
                it.copy(
                    devicesList = devicesListDeferred.await().sortedBy { device ->
                        device.pkDevice.toInt()
                    },
                    isRefreshing = false
                )
            }
        }
    }

    fun deleteDevice(device: Device) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDevice(device)

            val devicesListDeferred: Deferred<List<Device>> = async {
                repository.getSavedDevices()
            }

            _deviceListState.update {
                it.copy(
                    devicesList = devicesListDeferred.await().sortedBy { device ->
                        device.pkDevice.toInt()
                    }
                )
            }
        }
    }

    fun updateDeviceName(name: String, newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val devicesDeferred: Deferred<Device> = async {
                repository.getDeviceByName(name)
            }

            val device = devicesDeferred.await()
            device.name = newName

            repository.updateDevices(device)

            val devicesListDeferred: Deferred<List<Device>> = async {
                repository.getSavedDevices()
            }

            _deviceListState.update {
                it.copy(
                    devicesList = devicesListDeferred.await().sortedBy { device ->
                        device.pkDevice.toInt()
                    }
                )
            }
        }
    }
}