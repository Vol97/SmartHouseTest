package com.serhiivoloshyn.smarthousetest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiivoloshyn.smarthousetest.data.Device
import com.serhiivoloshyn.smarthousetest.data.DeviceListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
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
    private val _deviceListState = MutableStateFlow(DeviceListState(emptyList(), false))
    val deviceListState: StateFlow<DeviceListState> = _deviceListState

    init {
        viewModelScope.launch {
            _deviceListState.update {
                it.copy(devicesList = repository.getDevices() ?: emptyList())
            }
        }
    }

    fun refreshDevicesList() {
        viewModelScope.launch {
            val devicesListDeferred: Deferred<List<Device>> = async {
                repository.getDevices() ?: emptyList()
            }

            _deviceListState.update {
                it.copy(isRefreshing = true)
            }

            _deviceListState.update {
                it.copy(
                    devicesList = devicesListDeferred.await(),
                    isRefreshing = false
                )
            }
        }
    }
}