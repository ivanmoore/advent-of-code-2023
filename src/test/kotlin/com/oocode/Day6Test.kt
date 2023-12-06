package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day6Test {
    @Disabled
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """Time:      7  15   30
Distance:  9  40  200"""
        assertThat(racesFrom(input).recordFactor(), equalTo(288))
    }

    @Test
    fun calculatesCorrectAnswerForExamples() {
        assertThat(Race(length = 7, record = 9).numberOfWaysYouCouldWin(), equalTo(4))
        assertThat(Race(length = 15, record = 40).numberOfWaysYouCouldWin(), equalTo(8))
        assertThat(Race(length = 30, record = 200).numberOfWaysYouCouldWin(), equalTo(9))
    }

    @Test
    fun calculatesTimes() {
        assertThat(raceDistances(7), equalTo(listOf(6, 10, 12, 12, 10, 6)))
    }
}
