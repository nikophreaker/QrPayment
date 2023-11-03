package com.nikoprayogaw.qrpayment.domain.model.payment

import androidx.room.*

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPayment(payment: Payment)

    @Query("SELECT * FROM payment WHERE userId = :userId")
    suspend fun findPaymentByUserId(userId: String): Payment

    @Query("SELECT * FROM payment WHERE id = :id")
    suspend fun findPaymentById(id: Int): Payment

    @Query("SELECT * FROM payment")
    suspend fun getAllPaymentUser(): List<Payment>

    @Update
    suspend fun updatePaymentDetails(payment: Payment)

    @Delete
    suspend fun deletePayment(payment: Payment)
}