package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.serhiivoloshyn.smarthousetest.SmartHouseViewModel
import com.serhiivoloshyn.smarthousetest.data.Platform
import com.serhiivoloshyn.smarthousetest.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetails(
    navController: NavHostController,
    name: String?,
    pkDevice: String?,
    macAddress: String?,
    firmware: String?,
    platform: String?,
    editMode: Boolean?
) {
    val viewModel: SmartHouseViewModel = hiltViewModel()

    Column {
        UserHeader()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(10.dp),
                painter = painterResource(
                    id = Platform.getRequiredIcon(platform)
                ),
                contentDescription = "Device Icon"
            )

            Column(Modifier.padding(8.dp)) {
                if (editMode != null && editMode) {
                    val textField = remember { mutableStateOf(name) }
                    val focusRequester = remember { FocusRequester() }

                    TextField(
                        modifier = Modifier.focusRequester(focusRequester),
                        value = "${textField.value}",
                        onValueChange = { textField.value = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                viewModel.updateDeviceName(name!!, textField.value ?: "none")
                                navController.navigate(Screen.DeviceListScreen.route)
                            }
                        )
                    )

                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                    }
                } else {
                    Text(
                        text = "$name",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 30.sp
                    )
                }
                Text(
                    text = "SN: $pkDevice",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
                Text(
                    text = "MAC Address: $macAddress",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
                Text(
                    text = "Firmware: $firmware",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
                Text(
                    text = "Model: $platform",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            }
        }
    }
}