package com.serhiivoloshyn.smarthousetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.serhiivoloshyn.smarthousetest.ui.composables.DevicesList
import com.serhiivoloshyn.smarthousetest.ui.composables.UserHeader
import com.serhiivoloshyn.smarthousetest.ui.theme.SmartHouseTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartHouseTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                            DevicesList(devices = state.devicesList)
                        }

                        PullRefreshIndicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            refreshing = state.isRefreshing,
                            state = pullRefreshState
                        )
                    }
                }
            }
        }
    }
}