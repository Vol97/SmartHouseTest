package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.serhiivoloshyn.smarthousetest.data.Device
import com.serhiivoloshyn.smarthousetest.data.Platform

@Composable
fun DevicesList(navController: NavHostController, devices: List<Device>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = devices,
            key = { device ->
                device.pkDevice
            }
        ) { device ->
            DeviceListItem(
                navController = navController,
                device = device,
                icon = Platform.getRequiredIcon(device.platform)
            )
        }
    }
}