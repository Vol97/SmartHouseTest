package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.serhiivoloshyn.smarthousetest.SmartHouseViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppScreen(navController: NavHostController) {
    val viewModel: SmartHouseViewModel = hiltViewModel()
    val state by viewModel.deviceListState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { viewModel.refreshDevicesList() }
    )

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        Column {
            UserHeader()
            DevicesList(
                navController = navController,
                devices = state.devicesList
            )
        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = state.isRefreshing,
            state = pullRefreshState
        )
    }
}