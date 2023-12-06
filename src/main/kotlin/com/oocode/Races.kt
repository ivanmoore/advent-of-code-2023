package com.oocode

fun racesFrom(input: String): Races = TODO()

class Races {
    fun recordFactor(): Int {
        TODO("Not yet implemented")
    }
}

class Race(val length: Int, val record: Int) {
    fun numberOfWaysYouCouldWin(): Int {
        TODO("Not yet implemented")
    }
}

fun raceDistances(length: Int): List<Int> = IntRange(1, length - 1).map { distance(length, it) }

private fun distance(length: Int, timeToHoldDownButton: Int) =
    (length - timeToHoldDownButton) * timeToHoldDownButton
