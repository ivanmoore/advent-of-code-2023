package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import day1
import digits
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

    @Test
    fun digitifiesExampleCorrectly() {
        assertThat("""two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen""".split("\n").map { it.digits() }.joinToString("\n"),
            equalTo("""219
823
123
2134
49872
18234
76"""))
    }

    @Test
    fun digitifiesSingleExampleCorrectly() {
        assertThat("""two1nine""".digits(), equalTo("219"))
    }
}
