package com.nikoprayogaw.qrpayment

import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.domain.model.user.User
import com.nikoprayogaw.qrpayment.domain.repository.user.UserRepositoryImpl
import com.nikoprayogaw.qrpayment.domain.usecase.users.AddUserUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.GetSpecifiedUsersUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.GetUsersUseCase
import com.nikoprayogaw.qrpayment.domain.usecase.users.UpdateBalanceUserUseCase
import com.nikoprayogaw.qrpayment.presentation.viewmodel.UserViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject

@HiltAndroidTest
class UserViewModelTest {

    @Inject
    lateinit var repo: UserRepositoryImpl

    @Inject
    lateinit var getUsersUseCase: GetUsersUseCase

    @Inject
    lateinit var getSpecifiedUserUseCase: GetSpecifiedUsersUseCase

    @Inject
    lateinit var addUserUseCase: AddUserUseCase

    @Inject
    lateinit var updateBalanceUserUseCase: UpdateBalanceUserUseCase

    lateinit var userViewModel: UserViewModel // = mockk<UserViewModel>(relaxed = true)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

//    private val repo: UserRepositoryImpl = mockk(relaxed = true)

    @Test
    fun testDecodeBarcodeToData() {
        val qrcode = BitmapFactory.decodeResource(
            InstrumentationRegistry.getInstrumentation().targetContext.resources,
            R.mipmap.exampleqrcode
        )
        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
        val barcodeScanner = BarcodeScanning.getClient(options)
        val result = barcodeScanner.process(InputImage.fromBitmap(qrcode, 0))
        result.addOnSuccessListener{ barcodesListRaw ->
            val barcodeValue = barcodesListRaw.map { barcode -> barcode.rawValue }
            print("resultTest - $barcodeValue")
        }
        if (result.isSuccessful) {
            val res2 = result.result.map { barcode -> barcode.rawValue }
            print("resultTest - $res2")
            assert(res2.toString() == "BNI.ID12345678.MERCHANT MOCK TEST.50000")
        } else if (result.isComplete) {
            val res2 = result.result.map { barcode -> barcode.rawValue }
            print("resultTest - $res2")
            assert(res2.toString() == "BNI.ID12345678.MERCHANT MOCK TEST.50000")
        } else {
            assert(false)
        }
//        result.addOnSuccessListener{ barcodesListRaw ->
//            barcodesListRaw.forEach { barcodeRaw ->
//                barcodeRaw.rawValue?.let { barcodeValue ->
//                    val dataQr = barcodeValue.split(".")
//                    if (dataQr.size == 4) {
//                        return@addOnSuccessListener barcodeValue
////                            print("resultTest - $barcodeValue")
////                            assert(barcodeValue != "BNI.ID12345678.MERCHANT MOCK TEST.50000")
//                    } else {
//                        return@addOnSuccessListener ""
////                            assert(false)
//                    }
//                }
//            }
//        }.toString()
//        val barcode = barcodeScanner<OnSuccessListener<List<Barcode>>>()
//        every {
//        } returns "BNI.ID12345678.MERCHANT MOCK TEST.50000"



    }

    @Before
    fun init() {
        hiltRule.inject()
        userViewModel = UserViewModel(getUsersUseCase = getUsersUseCase, getSpecifiedUsersUseCase = getSpecifiedUserUseCase, addUserUseCase = addUserUseCase, updateBalanceUserUseCase = updateBalanceUserUseCase)
    }

    @Test
    fun testAddUser() {
        val user = mockk<User>()
        every { userViewModel.addUser(user) } returns Unit
    }

    @Test
    fun loadAllUser() = runTest {
        coEvery { repo.getAllUsers() } returns flow { Resource.Success(
            listOf<User>()
        ).data }
        userViewModel.getAllUser()
        coVerify { repo.getAllUsers() }
        userViewModel.resourceGetUsers.value.let { state ->
            assert(state is Resource.Success<*>)
        }
    }
}