package com.nikoprayogaw.qrpayment.presentation.paymentdetail

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.lifecycle.MutableLiveData
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.helper.CurrencyFormatter
import com.nikoprayogaw.qrpayment.helper.findActivity

@Composable
fun SuccessPaymentScreen() {
    val context = LocalContext.current
    val activity = context.findActivity()
    val intent = activity?.intent
    val bank = intent?.getStringExtra("bank")
    val transactionId = intent?.getLongExtra("transactionId", 0)
    val merchantName = intent?.getStringExtra("merchantName")
    val amount = intent?.getLongExtra("amount", 0)

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
                    text = "Pembayaran Berhasil",
                    fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "ID Transaksi: $transactionId",
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Merchant: $merchantName",
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Bank: $bank",
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Total Pembayaran: ${CurrencyFormatter(amount?:0)}",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
    Button(
        onClick = {
            activity?.finish()
        }
    ) {
        Text(text = "OK")
    }

}