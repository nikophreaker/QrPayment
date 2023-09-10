package com.nikoprayogaw.qrpayment.domain.model.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users WHERE accountNumber = :accountNumber")
    fun findUserByAccountNumber(accountNumber: Long): User

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Update
    suspend fun updateUserDetails(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}