package com.nikoprayogaw.qrpayment.presentation.screens.home

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.common.network.response.promo.PromoItem
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.presentation.compose.LoadingDialog
import com.nikoprayogaw.qrpayment.presentation.screens.promo.PromoDetailActivity
import com.nikoprayogaw.qrpayment.presentation.viewmodel.PromoViewModel
import com.nikoprayogaw.qrpayment.presentation.viewmodel.UserViewModel
import com.nikoprayogaw.qrpayment.helper.CurrencyFormatter
import com.nikoprayogaw.qrpayment.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    promoViewModel: PromoViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        userViewModel.getAllUser()
        userViewModel.findUserById(123456789)
        promoViewModel.getPromo()
    }
    val userDetail: User? by userViewModel.foundUser.observeAsState()
    val allUsers: List<User> by userViewModel.userList.observeAsState(initial = listOf())
    if (allUsers.isEmpty()) {
        userViewModel.addUser(User(
            accountNumber = 123456789,
            userName = "Niko Prayoga",
            email = "nikx449@gmail.com",
            phone = "085891334726",
            balance = 4600000
        ))
    }
    userViewModel.findUserById(123456789)
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
            style = MaterialTheme.typography.titleMedium
        )
        Surface(color = Color.White) {
            if (userDetail != null) UserCard(user = userDetail!!)
        }
        Text(
            text = "PROMO",
            style = MaterialTheme.typography.titleMedium
        )
        promoViewModel.resourcePromo.collectAsState().value.let { state ->
            when(state) {
                Resource.Idle -> {}
                Resource.Loading -> LoadingDialog(visible = true)
                is Resource.Success -> {
                    val promoList = state.data
                    LazyColumn {
                        items(items = promoList) { promo ->
                            PromoCard(promo = promo, context = context)
                            Divider()
                        }
                    }
                }
                is Resource.Error -> {}
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}

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
                }
            }
        }
    }
}

@Composable
fun PromoCard(promo: PromoItem, context: Context) {
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