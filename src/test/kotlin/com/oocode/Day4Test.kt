package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class Day4Test {
    @Test
    fun noCopies() {
        val input = """Card 1: 41 48 83 86 17 | 1 2 3 4"""
        val scratchCards = scratchCardsFrom(input).mapValues { entry -> entry.value.size }
        assertThat(scratchCards, equalTo(mapOf(1 to 1)))
    }

    @Test
    fun noCopies2() {
        val input = """Card 1: 41 48 83 86 17 | 1 2 3 4
Card 2: 13 32 20 16 61 | 1 2 3 4
        """.trimMargin()
        val scratchCards = scratchCardsFrom(input).mapValues { entry -> entry.value.size }
        assertThat(scratchCards, equalTo(mapOf(1 to 1, 2 to 1)))
    }

    @Test
    fun oneCopy() {
        val input = """Card 1: 41 48 83 86 17 | 1 2 3 48
Card 2: 13 32 20 16 61 | 1 2 3 4
        """.trimMargin()
        val scratchCards = scratchCardsFrom(input).mapValues { entry -> entry.value.size }
        assertThat(scratchCards, equalTo(mapOf(1 to 1, 2 to 2)))
    }

    @Test
    fun twoCopies() {
        val input = """Card 1: 41 48 83 86 17 | 17 2 3 48
Card 2: 13 32 20 16 61 | 1 2 3 4
Card 3:  1 21 53 59 44 | 2 3 4
        """.trimMargin()
        val scratchCards = scratchCardsFrom(input).mapValues { entry -> entry.value.size }
        assertThat(scratchCards, equalTo(mapOf(1 to 1, 2 to 2, 3 to 2)))
    }
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
        assertThat(scratchCardScoreFrom(input), equalTo(13))
        assertThat(scratchCardNumberFrom(input), equalTo(30))
    }

    @Test
    fun calculatesScoreForScratchCard() {
        val input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"
        val scratchCardComparison = scratchCardComparisonFrom(1, input)
        assertThat(
            scratchCardComparison, equalTo(
                ScratchCardComparison(
                    1,
                    setOf(41, 48, 83, 86, 17),
                    setOf(83, 86, 6, 31, 17, 9, 48, 53)
                )
            )
        )
        assertThat(scratchCardComparison.score(), equalTo(8))
    }

    @Test
    fun calculatesScoreForRubbishScratchCard() {
        val input = "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"
        val scratchCardComparison = scratchCardComparisonFrom(5, input)
        assertThat(scratchCardComparison.score(), equalTo(0))
    }

    @Test
    fun canCalculatePowerOfTwo() {
        assertThat(twoToPowerOf(0), equalTo(1))
        assertThat(twoToPowerOf(1), equalTo(2))
        assertThat(twoToPowerOf(2), equalTo(4))
        assertThat(twoToPowerOf(3), equalTo(8))
        assertThat(twoToPowerOf(4), equalTo(16))
    }
}
