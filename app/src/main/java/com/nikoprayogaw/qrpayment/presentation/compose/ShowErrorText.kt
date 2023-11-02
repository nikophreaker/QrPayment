package com.nikoprayogaw.qrpayment.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nikoprayogaw.qrpayment.common.extension.UiText

@Composable
fun ShowErrorText(isError: Boolean = false, errorMessage: UiText? = null) {
    val context = LocalContext.current
    Text(
        text = if (isError) errorMessage!!.asString(context) else "",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun ShowErrorText2(isError: Boolean = false, errorMessage: UiText? = null) {
    val context = LocalContext.current
    Text(
        text = if (isError) errorMessage!!.asString(context) else "",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    )
}