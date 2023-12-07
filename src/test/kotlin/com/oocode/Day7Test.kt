package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day7Test {
    @Disabled
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""
        assertThat(camelCardHandsFrom(input).totalWinnings(), equalTo(6440))
    }
}
