package com.nikoprayogaw.qrpayment.presentation.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CurrencyFormatter(amount: Long): String {
    val format: NumberFormat = NumberFormat.getInstance(Locale.getDefault())
    return format.format(amount)
}

@RequiresApi(Build.VERSION_CODES.O)
fun GetDateNow(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return LocalDateTime.now().format(formatter)
}