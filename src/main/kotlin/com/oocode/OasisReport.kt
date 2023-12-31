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
    fun extrapolatedValue(): Int = extrapolated()
        .map { it.first() }
        .foldRight(0, { i, accumulator ->
            i - accumulator
        })

    private fun extrapolated(): List<OasisHistory> =
        listOf(this) +
                if (history.all { it == 0 }) {
                    emptyList()
                } else {
                    OasisHistory(nextHistoryRow(history)).extrapolated()
                }

    private fun first() = history[0]
}

fun nextHistoryRow(input: List<Int>) =
    input.mapIndexed { index, i ->
        i - input[Math.max(0, index - 1)]
    }.drop(1)
