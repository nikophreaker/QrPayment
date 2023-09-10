package com.nikoprayogaw.qrpayment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nikoprayogaw.qrpayment.domain.model.payment.Payment
import com.nikoprayogaw.qrpayment.domain.model.payment.PaymentDao
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.model.user.UserDao


@Database(entities = [(User::class), (Payment::class)], version = 2, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun paymentDao(): PaymentDao

    internal class CallBack : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.beginTransaction()
            db.execSQL(
                "INSERT INTO users ('accountNumber','userName','email','phone','balance') VALUES (123456789,\"Niko Prayoga\", \"nikx449@gmail.com\", \"085891225824\",4600000)"
            )
            db.endTransaction()
        }
    }

    companion object {
        /*The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory.
        This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads.
        It means that changes made by one thread to INSTANCE are visible to all other threads immediately.*/
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "mbenking_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}