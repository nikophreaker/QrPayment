package com.nikoprayogaw.qrpayment.domain.model.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users WHERE accountNumber = :accountNumber")
    suspend fun findUserByAccountNumber(accountNumber: Long): User
    @Query("UPDATE users SET balance = balance - :amount WHERE accountNumber = :accountNumber")
    suspend fun updateBalance(accountNumber: Long, amount: Long)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Update
    suspend fun updateUserDetails(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}