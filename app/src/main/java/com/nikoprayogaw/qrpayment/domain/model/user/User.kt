package com.nikoprayogaw.qrpayment.domain.model.user

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "accountNumber")
    var accountNumber: Long,

    @ColumnInfo(name = "userName")
    var userName: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "phone")
    var phone: Long,

    @ColumnInfo(name = "balance")
    var balance: Long
) : Parcelable