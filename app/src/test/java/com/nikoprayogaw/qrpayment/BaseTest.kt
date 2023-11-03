package com.nikoprayogaw.qrpayment

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTest {

    @Before
    open fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    open fun after() {
        Dispatchers.resetMain()
    }
}