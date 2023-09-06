package com.serhiivoloshyn.smarthousetest.navigation

sealed class Screen(val route: String) {
    object DeviceListScreen: Screen(route = "devices_list")
    object DeviceDetailsScreen: Screen(route = "device_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}