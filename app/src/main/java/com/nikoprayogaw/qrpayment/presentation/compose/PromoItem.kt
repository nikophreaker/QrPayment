package com.nikoprayogaw.qrpayment.presentation.compose

import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoItem
import com.nikoprayogaw.qrpayment.presentation.screens.promo.PromoDetailActivity

@Composable
fun PromoCard(promo: PromoItem, context: Context) {
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        PromoDetailActivity::class.java
                    )
//                        .putExtra("img", promo.img?.formats?.small?.url)
                        .putExtra("PROMO_DATA", promo)
                )
            },
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
                    Image(
                        painter = rememberAsyncImagePainter(model = promo.img?.formats?.small?.url),
                        contentDescription = promo.alt
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${promo.nama}",
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}