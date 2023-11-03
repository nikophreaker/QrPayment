package com.nikoprayogaw.qrpayment.presentation.screens.chart

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.nikoprayogaw.qrpayment.common.network.Resource
import com.nikoprayogaw.qrpayment.presentation.compose.LoadingDialog
import com.nikoprayogaw.qrpayment.presentation.screens.mutasi.PaymentCard
import com.nikoprayogaw.qrpayment.presentation.viewmodel.PortoViewModel
import kotlin.random.Random

@Composable
fun ChartScreen(
    portoViewModel: PortoViewModel = hiltViewModel()
) {
    portoViewModel.resourcePorto.collectAsState().value.let { state ->
        when (state) {
            Resource.Idle -> {}
            Resource.Loading -> LoadingDialog(visible = true)
            is Resource.Success -> {
                val yearData: MutableList<Point> = mutableListOf()
                val monthData: MutableList<PieChartData.Slice> = mutableListOf()
                state.data.yearPorto?.data?.month?.forEachIndexed { index, i ->
                    yearData.add(Point((index).toFloat(), (i ?: 0).toFloat()))
                }
                state.data.monthPorto?.data?.forEach {
                    val rnd = Random.Default //kotlin.random
                    val color = android.graphics.Color.argb(
                        255,
                        rnd.nextInt(256),
                        rnd.nextInt(256),
                        rnd.nextInt(256)
                    )
                    monthData.add(
                        PieChartData.Slice(
                            it.label ?: "",
                            (it.percentage ?: "0").toFloat(),
                            Color(color)
                        )
                    )
                }
                CreateChart(yearData, monthData)
            }

            is Resource.Error -> {}
        }
    }

    LaunchedEffect(Unit) {
        portoViewModel.getPorto()
    }
}

@Composable
fun CreateChart(yearPorto: List<Point>, monthPorto: List<PieChartData.Slice>) {
    val listState = rememberLazyListState()
    val monthList = listOf("Jan", "Feb", "Mar", "Apr", "Mei", "Juni", "Juli", "Agst", "Sept", "Okt", "Nov", "Des")
    val xAxisData = AxisData.Builder()
        .axisStepSize(80.dp)
        .backgroundColor(Color.Blue)
        .steps(yearPorto.size - 1)
        .labelData { i -> monthList[i] }
        .labelAndAxisLinePadding(15.dp)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(yearPorto.size)
        .backgroundColor(Color.Red)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = (100 / yearPorto.size).toFloat()
            (i * yScale).formatToSinglePrecision()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = yearPorto,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )


    val donutChartData = PieChartData(
        slices = monthPorto,
        plotType = PlotType.Donut
    )
    val donutChartConfig = PieChartConfig(
        labelVisible = true,
        labelFontSize = 42.sp,
        strokeWidth = 120f,
        labelColor = Color.Black,
        activeSliceAlpha = .9f,
        isAnimationEnable = true
    )

    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
            state = listState
        ) {
            item {
                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    lineChartData = lineChartData,
                )
            }
            item {
                DonutPieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(5.dp),
                    pieChartData = donutChartData,
                    pieChartConfig = donutChartConfig
                )
            }
            item {
                monthPorto.forEach {
                    Surface(color = it.color, modifier = Modifier.fillMaxWidth()) {
                        Text(text = it.label)
                    }
                }
            }
        }
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
////            .verticalScroll(
////                rememberScrollState()
////            )
////            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize(),
//            state = listState
//        ) {
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(300.dp)
//                        .padding(8.dp)
//                ) {
//                    LineChart(modifier = Modifier.fillMaxWidth(), lineChartData = lineChartData)
//                }
//            }
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(300.dp)
//                        .padding(8.dp)
//                ) {
//                    DonutPieChart(
//                        modifier = Modifier.fillMaxWidth(),
//                        pieChartData = donutChartData,
//                        pieChartConfig = donutChartConfig
//                    )
//                }
//            }
//        }
//    }
}