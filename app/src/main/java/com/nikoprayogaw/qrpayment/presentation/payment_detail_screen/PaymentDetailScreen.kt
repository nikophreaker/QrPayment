package com.nikoprayogaw.qrpayment.presentation.payment_detail_screen

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.presentation.mutasi_screen.MutasiViewModel
import com.nikoprayogaw.qrpayment.presentation.util.CurrencyFormatter
import com.nikoprayogaw.qrpayment.presentation.util.GetDateNow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentDetailScreen(intent: Intent,
                        liveCycleOwner: LifecycleOwner,
                        navController: NavHostController,
                        mutasiViewModel: MutasiViewModel = hiltViewModel()
) {
    val bank = intent.getStringExtra("bank")
    val transactionId = intent.getLongExtra("transactionId",0)
    val merchantName = intent.getStringExtra("merchantName")
    val amount = intent.getLongExtra("amount", 0)
    val check = intent.getBooleanExtra("check", false)
    val success = MutableLiveData<Boolean>()

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 2.dp
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
                        text = "Total Pembayaran: ${CurrencyFormatter(amount)}",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }

    if (!check) {
        Button(
            onClick = {
                try {
                    mutasiViewModel.addPayment(
                        Payment(
                            userId = 1,
                            transactionId = transactionId.toString(),
                            bankName = bank.toString(),
                            merchantName = merchantName.toString(),
                            amount = amount,
                            date = GetDateNow()
                        )
                    )
                    success.value = true
                } catch (err: Exception) {
                    success.value = false
                    Toast.makeText(navController.context,"Pembayaran Gagal",Toast.LENGTH_SHORT).show()

                }
            }
        ) {
            Text(text = "Bayar")
        }
    }

    success.observe(liveCycleOwner) {
        if (it) {

        }
    }
}