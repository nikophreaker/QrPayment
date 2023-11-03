package com.nikoprayogaw.qrpayment.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.presentation.compose.LoadingDialog
import com.nikoprayogaw.qrpayment.presentation.viewmodel.PromoViewModel
import com.nikoprayogaw.qrpayment.presentation.viewmodel.UserViewModel
import com.nikoprayogaw.qrpayment.presentation.compose.PromoCard
import com.nikoprayogaw.qrpayment.presentation.compose.UserCard

@Composable
fun HomeScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    promoViewModel: PromoViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userDetail: MutableState<User?> = remember {
        mutableStateOf(null)
    }

    LaunchedEffect(Unit) {
        userViewModel.getAllUser()
        userViewModel.findUserById(123456789)
        promoViewModel.getPromo()
    }
    userViewModel.resourceGetUser.collectAsState().value.let { state ->
        when(state) {
            Resource.Idle -> {}
            Resource.Loading -> LoadingDialog(visible = true)
            is Resource.Success -> {
                userDetail.value = state.data
            }
            is Resource.Error -> {}
        }
    }
    userViewModel.resourceGetUsers.collectAsState().value.let { state ->
        when(state) {
            Resource.Idle -> {}
            Resource.Loading -> LoadingDialog(visible = true)
            is Resource.Success -> {
                userViewModel.clearedGetUsers()
                val allUsers = state.data
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
            }
            is Resource.Error -> {}
        }
    }
    userViewModel.resourceAddUser.collectAsState().value.let { state ->
        when(state) {
            Resource.Idle -> {}
            Resource.Loading -> LoadingDialog(visible = true)
            is Resource.Success -> {
                userViewModel.clearedAddUser()
            }
            is Resource.Error -> {}
        }
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
            text = "Welcome to the MBanking",
            style = MaterialTheme.typography.titleMedium
        )
        Surface(color = Color.White) {
            if (userDetail.value != null) UserCard(user = userDetail.value!!)
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



