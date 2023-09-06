package com.serhiivoloshyn.smarthousetest.data

data class DeviceListState(
    val devicesList: List<Device>,
    val isRefreshing: Boolean
)
