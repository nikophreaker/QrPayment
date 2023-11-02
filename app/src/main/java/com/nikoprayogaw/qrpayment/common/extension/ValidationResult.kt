package com.nikoprayogaw.qrpayment.common.extension

import android.content.Context
import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)

fun isNumber(value: String): Boolean {
    return value.isEmpty() || Regex("^\\d+\$").matches(value)
}

fun isDecimal(value: String): Boolean {
    return value.isEmpty() || Regex("^[1-9]\\d*(\\.\\d+)?\$").matches(value)
}

fun isPhoneNumber(mobile: String): Boolean {
    return ( mobile.isNotEmpty() && mobile.length >= 10)
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}