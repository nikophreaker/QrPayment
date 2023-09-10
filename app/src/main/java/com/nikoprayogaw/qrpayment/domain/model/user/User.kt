package com.nikoprayogaw.qrpayment.domain.model.user

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "accountNumber")
    var accountNumber: Long,

    @ColumnInfo(name = "userName")
    var userName: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "balance")
    var balance: Long
) : Parcelable {

}