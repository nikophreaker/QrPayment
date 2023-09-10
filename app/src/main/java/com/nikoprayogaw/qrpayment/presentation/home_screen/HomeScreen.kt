package com.nikoprayogaw.qrpayment.presentation.home_screen

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.presentation.user_screen.UserViewModel
import com.nikoprayogaw.qrpayment.presentation.util.CurrencyFormatter

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    userViewModel.getAllUser()
    userViewModel.findUserById(123456789)

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