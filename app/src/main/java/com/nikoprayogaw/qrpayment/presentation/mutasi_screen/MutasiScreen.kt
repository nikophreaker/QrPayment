package com.nikoprayogaw.qrpayment.presentation.mutasi_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoprayogaw.qrpayment.R

@Composable
fun MutasiScreen(
    mutasiViewModel: MutasiViewModel = hiltViewModel()
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
                onClick = { mutasiViewModel.onBackButtonClicked() }
            ) {
                Icon(painter = painterResource(R.mipmap.document), contentDescription = "")
            }
        }
        Text(
            text = "MutasiScreen",
            style = MaterialTheme.typography.h5
        )
    }
}