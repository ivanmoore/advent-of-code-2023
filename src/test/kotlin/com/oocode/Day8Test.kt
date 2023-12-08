package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.oocode.Choice.Left
import com.oocode.Choice.Right
import org.junit.jupiter.api.Test

internal class Day8Test {
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

    @Test
    fun calculatesCorrectAnswersForAnotherExample() {
        val input = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)"""
        assertThat(directionsFrom(input).numberOfSteps(), equalTo(6))
    }

    @Test
    fun parsesInput() {
        val input = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
ZZZ = (ZZZ, ZZZ)"""
        assertThat(directionsFrom(input),
            equalTo(CamelMap(
                listOf(Right, Left),
                listOf(
                    Node("AAA", Children("BBB", "CCC")),
                    Node("BBB", Children("DDD", "EEE")),
                    Node("ZZZ", Children("ZZZ", "ZZZ")))
            )))
    }
}
