package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day9Test {
    @Disabled
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45"""
        assertThat(oasisReportFrom(input).extrapolatedValuesSum(), equalTo(114))
    }

    @Disabled
    @Test
    fun calculatesCorrectAnswersForSingleExamples() {
        assertThat(
            OasisHistory(listOf(0, 3, 6, 9, 12, 15))
                .extrapolatedValue(), equalTo(18)
        )
        assertThat(
            OasisHistory(listOf(1, 3, 6, 10, 15, 21))
                .extrapolatedValue(), equalTo(28)
        )
        assertThat(
            OasisHistory(listOf(10, 13, 16, 21, 30, 45))
                .extrapolatedValue(), equalTo(68)
        )
    }

    @Test
    fun canParseInput() {
        val input = """0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45"""
        assertThat(
            oasisReportFrom(input),
            equalTo(
                OasisReport(
                    listOf(
                        OasisHistory(listOf(0, 3, 6, 9, 12, 15)),
                        OasisHistory(listOf(1, 3, 6, 10, 15, 21)),
                        OasisHistory(listOf(10, 13, 16, 21, 30, 45))
                    )
                )
            )
        )
    }

    @Test
    fun canFindNextRow() {
        assertThat(nextHistoryRow(listOf(0, 3, 6, 9, 12, 15)), equalTo(listOf(3, 3, 3, 3, 3)))
        assertThat(nextHistoryRow(listOf(3, 3, 3, 3, 3)), equalTo(listOf(0, 0, 0, 0)))
        assertThat(nextHistoryRow(listOf(10, 13, 16, 21, 30, 45)), equalTo(listOf(3, 3, 5, 9, 15)))
        assertThat(nextHistoryRow(listOf(3, 3, 5, 9, 15)), equalTo(listOf(0, 2, 4, 6)))
        assertThat(nextHistoryRow(listOf(0, 2, 4, 6)), equalTo(listOf(2, 2, 2)))
        assertThat(nextHistoryRow(listOf(2, 2, 2)), equalTo(listOf(0, 0)))
    }
}
