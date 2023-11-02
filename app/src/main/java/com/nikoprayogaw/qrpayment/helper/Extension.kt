package com.nikoprayogaw.qrpayment.helper

import android.app.Activity
import android.content.*
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CurrencyFormatter(amount: Long): String {
    val format: NumberFormat = NumberFormat.getInstance(Locale.getDefault())
    return format.format(amount)
}

fun GetDateNow(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return LocalDateTime.now().format(formatter)
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}