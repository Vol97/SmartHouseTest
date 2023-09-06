package com.serhiivoloshyn.smarthousetest.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhiivoloshyn.smarthousetest.R

@Composable
fun UserHeader() {
    val configuration = LocalConfiguration.current

    val desiredHeight = (configuration.screenHeightDp * 0.3).dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(desiredHeight)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.DarkGray),
            painter = painterResource(
                id = R.drawable.ic_launcher_foreground
            ),
            contentDescription = "User Icon"
        )

        Text(
            text = "Serhii Voloshyn",
            fontSize = 30.sp
        )
    }
}