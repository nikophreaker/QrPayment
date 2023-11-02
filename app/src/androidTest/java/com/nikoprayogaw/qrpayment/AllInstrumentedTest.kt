package com.nikoprayogaw.qrpayment

import android.graphics.BitmapFactory
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import io.mockk.every
import io.mockk.verify
import org.junit.*


class AllInstrumentedTest {
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
}