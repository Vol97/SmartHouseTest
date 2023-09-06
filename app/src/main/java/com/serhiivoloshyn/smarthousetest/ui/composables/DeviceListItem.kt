package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiivoloshyn.smarthousetest.data.Device

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeviceListItem(device: Device, header: String, icon: Int) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(100.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = {}
            ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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
                    text = header,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 24.sp
                )
                Text(
                    text = "SN: ${device.pkDevice}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp
                )
            }
        }
    }
}