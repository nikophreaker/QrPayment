package com.nikoprayogaw.qrpayment.helper

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String, long: Boolean) =
    if (long) Toast.makeText(this, message, Toast.LENGTH_LONG).show() else Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()