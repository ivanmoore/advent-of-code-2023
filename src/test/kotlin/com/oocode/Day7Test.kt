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
            "AAAAA" to "AA8AA",
            "AA8AA" to "23332",
            "23332" to "TTT98",
            "TTT98" to "23432",
            "23432" to "A23A4",
            "A23A4" to "23456"
        )
        for (pair in inWinningOrder) {
            assertWinner(pair.first, pair.second)
        }
    }

    private fun assertWinner(winner: String, loser: String) {
        val message = "comparing $winner vs $loser"
        assertTrue(camelCardHandFrom(winner) > camelCardHandFrom(loser), message)
        assertFalse(camelCardHandFrom(winner) < camelCardHandFrom(loser), message)
        assertTrue(camelCardHandFrom(loser) < camelCardHandFrom(winner), message)
        assertFalse(camelCardHandFrom(loser) > camelCardHandFrom(winner), message)

        assertTrue(camelCardHandFrom(winner) != camelCardHandFrom(loser), message)
        assertTrue(camelCardHandFrom(loser) != camelCardHandFrom(winner), message)
        assertTrue(camelCardHandFrom(winner) == camelCardHandFrom(winner), message)
        assertTrue(camelCardHandFrom(loser) == camelCardHandFrom(loser), message)
    }
}
