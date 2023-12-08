package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.oocode.Choice.Left
import com.oocode.Choice.Right
import org.junit.jupiter.api.Disabled
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

    @Disabled
    @Test
    fun calculatesCorrectAnswersForGhostExample() {
        val input = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)"""
        assertThat(directionsFrom(input).numberOfSteps(), equalTo(6))
    }
}
