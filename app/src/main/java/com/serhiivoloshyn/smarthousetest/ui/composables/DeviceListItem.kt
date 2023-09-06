package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.serhiivoloshyn.smarthousetest.R
import com.serhiivoloshyn.smarthousetest.SmartHouseViewModel
import com.serhiivoloshyn.smarthousetest.data.Device
import com.serhiivoloshyn.smarthousetest.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeviceListItem(
    navController: NavHostController,
    device: Device,
    icon: Int
) {
    val viewModel: SmartHouseViewModel = hiltViewModel()
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        CustomAlertDialog(
            title = "Delete item",
            mainText = "Are you sure that you want to delete this item?",
            deleteFunction = { viewModel.deleteDevice(device) },
            onDismiss = { showDialog.value = false },
            showDialog = showDialog.value
        )
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .combinedClickable(
                onClick = {
                    navController.navigate(
                        Screen.DeviceDetailsScreen.withArgs(
                            device.name ?: "none",
                            device.pkDevice,
                            device.macAddress,
                            device.firmware,
                            device.platform.name,
                            "false"
                        )
                    )
                },
                onLongClick = {
                    showDialog.value = true
                }
            ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(10.dp),
                painter = painterResource(id = icon),
                contentDescription = "Device Icon"
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = device.name!!,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 24.sp
                )
                Text(
                    text = "SN: ${device.pkDevice}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            navController.navigate(
                                Screen.DeviceDetailsScreen.withArgs(
                                    device.name ?: "none",
                                    device.pkDevice,
                                    device.macAddress,
                                    device.firmware,
                                    device.platform.name,
                                    "true"
                                )
                            )
                        },
                    painter = painterResource(R.drawable.edit_icon),
                    contentDescription = "Edit icon",
                    alignment = Alignment.CenterEnd
                )
            }
        }
    }
}