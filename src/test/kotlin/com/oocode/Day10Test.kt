package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isIn
import org.junit.jupiter.api.Test

internal class Day10Test {
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

    @Test
    fun findsStart() {
        val input = """
-L|F7
7S-7|
L|7||
-L-J|
L|-JF""".trim()
        assertThat(pipesGridFrom(input).start(), equalTo(Tile(START, Position(1, 1))))
    }

    @Test
    fun canFindNeighbours() {
        val input = """
-L|F7
7S-7|
L|7||
-L-J|
L|-JF""".trim()
        assertThat(
            pipesGridFrom(input).neighbouringTiles(Position(0, 0)).map { it.directionTowardsThis }.toSet(),
            equalTo(setOf(Compass.East, Compass.South)))
        assertThat(pipesGridFrom(input).neighbouringTiles(Position(0, 1)).map { it.directionTowardsThis }.toSet(),
            equalTo(setOf(Compass.East, Compass.South, Compass.North)))
        assertThat(pipesGridFrom(input).neighbouringTiles(Position(1, 1)).map { it.directionTowardsThis }.toSet(),
            equalTo(setOf(Compass.East, Compass.South, Compass.North, Compass.West)))
        assertThat(
            pipesGridFrom(input).neighbouringTiles(Position(1, 4)).map { it.directionTowardsThis }.toSet(),
            equalTo(setOf(Compass.East, Compass.North, Compass.West)))
        assertThat(
            pipesGridFrom(input).neighbouringTiles(Position(4, 4)).map { it.directionTowardsThis }.toSet(),
            equalTo(setOf(Compass.North, Compass.West)))

        assertThat(pipesGridFrom(input).neighbouringTiles(Position(1, 1)).map { it.tile }.toSet(),
            equalTo(setOf(
                Tile(NE, Position(1, 0)),
                Tile(SW, Position(0, 1)),
                Tile(EW, Position(2, 1)),
                Tile(NS, Position(1, 2)))))
    }

    @Test
    fun findsNextFromStart() {
        val input = """
-L|F7
7S-7|
L|7||
-L-J|
L|-JF""".trim()
        val start = Tile(START, Position(1, 1))
        val nextTile = pipesGridFrom(input).nextTile(start, emptyList())
        assertThat("nextTile=$nextTile", nextTile, isIn(
            Tile(EW, Position(2, 1)),
            Tile(NS, Position(1, 2))))
    }

    @Test
    fun findsPathFromStart() {
        val input = """
-L|F7
7S-7|
L|7||
-L-J|
L|-JF""".trim()
        val path = pipesGridFrom(input).path().map { it.type.toString() }
        assertThat("path=$path", path, isIn(
            listOf("S", "-", "7", "|", "J", "-", "L", "|"),
            listOf("S", "|", "L", "-", "J", "|", "7", "-")
        ))
    }
}
