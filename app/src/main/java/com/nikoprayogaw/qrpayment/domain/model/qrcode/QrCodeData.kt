package com.nikoprayogaw.qrpayment.domain.model.qrcode

import java.io.Serializable

data class QrCodeData(
    var bank: String? = null,
    var transactionId: String? = null,
    var merchantName: String? = null,
    var amount: String? = null
) : Serializable