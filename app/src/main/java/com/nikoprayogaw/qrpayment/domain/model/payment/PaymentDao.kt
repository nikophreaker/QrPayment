package com.nikoprayogaw.qrpayment.domain.model.payment

import androidx.room.*

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPayment(payment: Payment)

    @Query("SELECT * FROM payment WHERE userId = :userId")
    fun findPaymentByUserId(userId: String): Payment

    @Query("SELECT * FROM payment")
    fun getAllPaymentUser(): List<Payment>

    @Update
    suspend fun updatePaymentDetails(payment: Payment)

    @Delete
    suspend fun deletePayment(payment: Payment)
}