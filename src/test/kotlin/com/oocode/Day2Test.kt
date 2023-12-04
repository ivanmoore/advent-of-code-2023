package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2Test {
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"""
        val bag = Bag(red = 12, green = 13, blue = 14)
        assertThat(bag.possibilityTotal(input), equalTo(8))
        assertThat(powerOf(input), equalTo(2286))
    }

    @Test
    fun parsesLinesIntoGames() {
        assertEquals(
            gameFrom("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
            Game(1, setOf(
                Reveal(blue = 3, red = 4),
                Reveal(red = 1, green = 2, blue = 6),
                Reveal(green = 2)
            )))
    }

    @Test
    fun canCalculateMinimumBag() {
        assertEquals(
            gameFrom("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").minimumBag(),
            Bag(red = 4, green = 2, blue = 6))
    }

    @Test
    fun canCalculateBagPower() {
        assertEquals(
            Bag(red = 4, green = 2, blue = 6).power(),
            48
        )
    }
}
