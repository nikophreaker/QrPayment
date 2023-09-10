package com.nikoprayogaw.qrpayment.domain.model.payment

import android.os.Parcelable
import androidx.room.*
import com.nikoprayogaw.qrpayment.domain.model.user.User
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(
    tableName = "payment", foreignKeys =
    [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Payment(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "transactionId")
    var transactionId: String,

    @ColumnInfo(name = "userId", index = true)
    var userId: Int,

    @ColumnInfo(name = "merchantName")
    var merchantName: String,

    @ColumnInfo(name = "bankName")
    var bankName: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "amount")
    var amount: Long
) : Parcelable