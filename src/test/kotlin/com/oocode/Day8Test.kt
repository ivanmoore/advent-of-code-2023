package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day8Test {
    @Disabled
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)"""
        assertThat(directionsFrom(input).numberOfSteps(), equalTo(2))
    }

    @Disabled
    @Test
    fun calculatesCorrectAnswersForAnotherExample() {
        val input = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)"""
        assertThat(directionsFrom(input).numberOfSteps(), equalTo(6))
    }
}
