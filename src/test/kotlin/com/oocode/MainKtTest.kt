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
                """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet"""
            ), equalTo(142)
        )
    }
}
