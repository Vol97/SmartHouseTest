package com.serhiivoloshyn.smarthousetest.ui.composables

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun CustomAlertDialog(title: String, mainText: String) {
    val openDialog = remember { mutableStateOf(true) }
    val context = LocalContext.current

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = mainText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        (context as Activity).finish()
                    }
                ) {
                    Text(text = "Close the app")
                }
            }
        )
    }
}