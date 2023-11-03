package com.nikoprayogaw.qrpayment.presentation.compose

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.helper.CurrencyFormatter

@Composable
fun UserCard(user: User) {
    ElevatedCard(
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
                        text = user.userName,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Balance: ${CurrencyFormatter(user.balance)}",
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Phone Number: ${user.phone}",
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Account Number: ${user.accountNumber}",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}