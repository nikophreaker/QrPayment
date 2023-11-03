package com.nikoprayogaw.qrpayment.presentation.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikoprayogaw.qrpayment.R
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.presentation.compose.LoadingDialog
import com.nikoprayogaw.qrpayment.presentation.compose.UserCard
import com.nikoprayogaw.qrpayment.presentation.viewmodel.UserViewModel

@Composable
fun UserScreen(
    userViewModel: UserViewModel = hiltViewModel()
) {
    val userDetail: MutableState<User?> = remember {
        mutableStateOf(null)
    }
    LaunchedEffect(Unit) {
        userViewModel.findUserById(123456789)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (userDetail.value != null) {
            UserCard(user = userDetail.value!!)
        }
    }
}