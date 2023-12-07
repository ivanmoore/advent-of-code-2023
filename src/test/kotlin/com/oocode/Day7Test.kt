package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
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

    @Test
    fun canRankHandsComparedToEachOther() {
        val inWinningOrder = listOf(
            "AAAAA" to "AA8AA"
        )
        for (pair in inWinningOrder) {
            assertWinner(pair.first, pair.second)
        }
    }

    private fun assertWinner(winner: String, loser: String) {
        assertTrue(camelCardHandFrom(winner) > camelCardHandFrom(loser))
        assertFalse(camelCardHandFrom(winner) < camelCardHandFrom(loser))
        assertTrue(camelCardHandFrom(loser) < camelCardHandFrom(winner))
        assertFalse(camelCardHandFrom(loser) > camelCardHandFrom(winner))

        assertTrue(camelCardHandFrom(winner) != camelCardHandFrom(loser))
        assertTrue(camelCardHandFrom(loser) != camelCardHandFrom(winner))
        assertTrue(camelCardHandFrom(winner) == camelCardHandFrom(winner))
        assertTrue(camelCardHandFrom(loser) == camelCardHandFrom(loser))
    }
}
