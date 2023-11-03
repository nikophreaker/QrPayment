package com.nikoprayogaw.qrpayment.presentation.screens.promo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoItem

class PromoDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val promoData = intent.getSerializableExtra("PROMO_DATA", PromoItem::class.java)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    PromoDetailScreen(promoData)
                }
            }
        }
    }
}