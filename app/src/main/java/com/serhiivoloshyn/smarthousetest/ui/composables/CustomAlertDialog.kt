package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CustomAlertDialog(
    showDialog: Boolean,
    title: String,
    mainText: String,
    onDismiss: () -> Unit,
    deleteFunction: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss.invoke() },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = mainText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismiss.invoke()
                        deleteFunction.invoke()
                    }
                ) {
                    Text(text = "Delete item")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss.invoke()
                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}