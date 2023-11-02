package com.nikoprayogaw.qrpayment.presentation.navigation.model

import org.json.JSONObject

sealed class GeneralScreen(val route: String) {

    data object Home : GeneralScreen("home")
    data object ScanQr : GeneralScreen("home/scan-qr")
    data object Chart : GeneralScreen("home/chart")
    data object Mutation : GeneralScreen("home/mutation")
    data object DetailUser : GeneralScreen("home/detail-user/{idUser}"){
        fun createRoute(idUser: Int) = "home/detail-user/$idUser"
    }
    data object DetailPayment : GeneralScreen("home/detail-payment/{idPayment}"){
        fun createRoute(idPayment: Int) = "home/detail-payment/$idPayment"
    }
    data object PaymentSuccess : GeneralScreen("home/payment-success")


}