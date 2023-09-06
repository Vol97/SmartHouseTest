package com.serhiivoloshyn.smarthousetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.serhiivoloshyn.smarthousetest.ui.composables.AppScreen
import com.serhiivoloshyn.smarthousetest.ui.composables.DeviceDetails

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DeviceListScreen.route
    ) {
        composable(route = Screen.DeviceListScreen.route) {
            AppScreen(navController = navController)
        }
        composable(
            route = Screen.DeviceDetailsScreen.route + "/{name}/{pkDevice}/{macAddress}/{firmware}/{platform}/{editMode}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("pkDevice") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("macAddress") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("firmware") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("platform") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("editMode") {
                    type = NavType.BoolType
                    nullable = false
                }
            )
        ) { entry ->
            DeviceDetails(
                navController = navController,
                name = entry.arguments?.getString("name"),
                pkDevice = entry.arguments?.getString("pkDevice"),
                macAddress = entry.arguments?.getString("macAddress"),
                firmware = entry.arguments?.getString("firmware"),
                platform = entry.arguments?.getString("platform"),
                editMode = entry.arguments?.getBoolean("editMode")
            )
        }
    }
}