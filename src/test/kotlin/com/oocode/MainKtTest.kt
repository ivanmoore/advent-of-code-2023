package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import day1
import org.junit.jupiter.api.Test

internal class MainKtTest {
    @Test
    fun calculatesCorrectAnswerForExample() {
        assertThat(
            day1(
                """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen"""
            ), equalTo(281)
        )
    }
}
