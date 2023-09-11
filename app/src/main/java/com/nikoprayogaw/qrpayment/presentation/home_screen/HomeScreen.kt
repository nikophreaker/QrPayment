package com.nikoprayogaw.qrpayment.presentation.home_screen

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.model.promo.PromoItem
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.presentation.payment_detail_screen.PaymentDetailActivity
import com.nikoprayogaw.qrpayment.presentation.promo_screen.PromoDetailActivity
import com.nikoprayogaw.qrpayment.presentation.promo_screen.PromoViewModel
import com.nikoprayogaw.qrpayment.presentation.user_screen.UserViewModel
import com.nikoprayogaw.qrpayment.presentation.util.CurrencyFormatter

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    promoViewModel: PromoViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        userViewModel.getAllUser()
        userViewModel.findUserById(123456789)
        promoViewModel.fetchPromo()
    }
    val userDetail: User? by userViewModel.foundUser.observeAsState()
    val allUsers: List<User> by userViewModel.userList.observeAsState(initial = listOf())
    val promos by promoViewModel.promos.observeAsState(emptyList())
    if (allUsers.isEmpty()) {
        userViewModel.addUser(User(
            accountNumber = 123456789,
            userName = "Niko Prayoga",
            email = "nikx449@gmail.com",
            phone = "085891334726",
            balance = 4600000
        ))
        userViewModel.findUserById(123456789)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the MBenking",
            style = MaterialTheme.typography.h5
        )
        Surface(color = Color.White) {
            if (userDetail != null) UserCard(user = userDetail!!)
        }
        Text(
            text = "PROMO",
            style = MaterialTheme.typography.h6
        )
        if (promos.isEmpty()) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            // Display the list of credit cards
            LazyColumn {
                items(items = promos) { promo ->
                    PromoCard(promo = promo, context = context)
                    Divider()
                }
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
fun UserCard(user: User) {
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
                        text = user.userName,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Balance: ${CurrencyFormatter(user.balance)}",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun PromoCard(promo: PromoItem, context: Context) {
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
                    context.startActivity(
                        Intent(
                            context,
                            PromoDetailActivity::class.java
                        )
                            .putExtra("img", promo.img?.formats?.small?.url)
                            .putExtra("promoName", promo.nama)
                    )
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