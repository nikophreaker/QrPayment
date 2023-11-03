package com.nikoprayogaw.qrpayment.presentation.screens.mutasi

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.model.qrcode.QrCodeData
import com.nikoprayogaw.qrpayment.presentation.viewmodel.MutationViewModel
import com.nikoprayogaw.qrpayment.helper.CurrencyFormatter
import com.nikoprayogaw.qrpayment.helper.GetDateNow
import com.nikoprayogaw.qrpayment.helper.findActivity
import com.nikoprayogaw.qrpayment.presentation.compose.LoadingDialog
import com.nikoprayogaw.qrpayment.presentation.viewmodel.UserViewModel

@Composable
fun MutasiDetailScreen(
    mutationViewModel: MutationViewModel = hiltViewModel(),
    idPayment: Int
) {
    val dataPayment: MutableState<Payment?> = remember {
        mutableStateOf(null)
    }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        LaunchedEffect(Unit) {
            mutationViewModel.getDetailPayment(idPayment)
        }
        mutationViewModel.resourceGetPaymentById.collectAsState().value.let { state ->
            when (state) {
                Resource.Idle -> {}
                Resource.Loading -> LoadingDialog(visible = true)
                is Resource.Success -> {
                    dataPayment.value = state.data
                }
                is Resource.Error -> {}
            }
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                }
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.mipmap.user),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        colorResource(id = R.color.purple_500),
                    ),
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(50)),
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = "ID Transaksi: ${dataPayment.value?.transactionId}",
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Merchant: ${dataPayment.value?.merchantName}",
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Bank: ${dataPayment.value?.bankName}",
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Total Pembayaran: ${CurrencyFormatter(dataPayment.value?.amount?.toLong() ?: 0)}",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}