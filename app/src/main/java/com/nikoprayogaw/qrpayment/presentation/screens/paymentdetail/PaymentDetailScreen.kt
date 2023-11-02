package com.nikoprayogaw.qrpayment.presentation.screens.paymentdetail

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
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.model.qrcode.QrCodeData
import com.nikoprayogaw.qrpayment.presentation.viewmodel.MutationViewModel
import com.nikoprayogaw.qrpayment.helper.CurrencyFormatter
import com.nikoprayogaw.qrpayment.helper.GetDateNow
import com.nikoprayogaw.qrpayment.helper.findActivity
import com.nikoprayogaw.qrpayment.presentation.viewmodel.UserViewModel

@Composable
fun PaymentDetailScreen(
    lifeCycleOwner: LifecycleOwner,
    mutationViewModel: MutationViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToSuccess: () -> Unit
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val intent = activity?.intent
    val dataQrCode = intent?.getSerializableExtra("QR_STRING", QrCodeData::class.java)
    val check = intent?.getBooleanExtra("check", false) ?: false
    val success = MutableLiveData<Boolean>()

    Scaffold(
        bottomBar = {
            if (!check) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        try {
                            mutationViewModel.addPayment(
                                Payment(
                                    userId = 1,
                                    transactionId = dataQrCode?.transactionId ?: "",
                                    bankName = dataQrCode?.bank ?: "",
                                    merchantName = dataQrCode?.merchantName ?: "",
                                    amount = dataQrCode?.amount?.toLong() ?: 0,
                                    date = GetDateNow()
                                )
                            )
                            navigateToSuccess.invoke()
                        } catch (err: Exception) {
                            Toast.makeText(context, "Pembayaran Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(text = "Bayar")
                }
            }
        }
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .padding(it)
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
                            text = "ID Transaksi: ${dataQrCode?.transactionId}",
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Merchant: ${dataQrCode?.merchantName}",
                            fontSize = 14.sp,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Bank: ${dataQrCode?.bank}",
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Total Pembayaran: ${CurrencyFormatter(dataQrCode?.amount?.toLong() ?: 0)}",
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }

    success.observe(lifeCycleOwner) {
        if (it) {
            navigateToBack.invoke()
        }
    }
}