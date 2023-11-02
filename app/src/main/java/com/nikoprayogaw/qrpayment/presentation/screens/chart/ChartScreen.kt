package com.nikoprayogaw.qrpayment.presentation.screens.chart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.presentation.viewmodel.ChartViewModel

@Composable
fun ChartScreen(
    navigateToBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = navigateToBack
            ) {
                Icon(painter = painterResource(R.mipmap.document), contentDescription = "")
            }
        }
        Text(
            text = "ChartScreen",
            style = MaterialTheme.typography.titleMedium
        )
    }
}