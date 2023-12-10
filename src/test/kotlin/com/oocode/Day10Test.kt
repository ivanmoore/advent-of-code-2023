package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day10Test {
    @Disabled
    @Test
    fun calculatesCorrectAnswersForSmallExample() {
        val input = """
-L|F7
7S-7|
L|7||
-L-J|
L|-JF""".trim()
        assertThat(pipesGridFrom(input).furthestDistanceFromStart(), equalTo(4))
    }

    @Disabled
    @Test
    fun calculatesCorrectAnswersForBiggerExample() {
        val input = """
7-F7-
.FJ|7
SJLL7
|F--J
LJ.LJ""".trim()
        assertThat(pipesGridFrom(input).furthestDistanceFromStart(), equalTo(8))
    }

    @Test
    fun canParseInput() {
        val input = """
S-7
|.F
L-J""".trim()
        assertThat(
            pipesGridFrom(input), equalTo(
                PipesGrid(
                    listOf(
                        listOf(tileFor("S", 0, 0), tileFor("-", 1, 0), tileFor("7", 2, 0)),
                        listOf(tileFor("|", 0, 1), tileFor(".", 1, 1), tileFor("F", 2, 1)),
                        listOf(tileFor("L", 0, 2), tileFor("-", 1, 2), tileFor("J", 2, 2))
                    )
                )
            )
        )
    }
}
