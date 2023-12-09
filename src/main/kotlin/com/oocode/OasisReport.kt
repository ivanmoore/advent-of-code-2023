package com.oocode

fun oasisReportFrom(input: String): OasisReport =
    OasisReport(input
        .split("\n")
        .map { line -> OasisHistory(line.split(" ").map { it.toInt() }) })

data class OasisReport(private val histories: List<OasisHistory>) {
    fun extrapolatedValuesSum(): Int {
        return histories.map { it.extrapolatedValue() }.sum()
    }
}

data class OasisHistory(private val history: List<Int>) {
    fun extrapolatedValue(): Int {
        TODO()
    }
}
