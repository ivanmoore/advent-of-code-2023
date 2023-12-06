package com.oocode

fun racesFrom(input: String): Races {
    val lines = input.split("\n")
    val raceLengths = numbersFrom(lines[0])
    val raceRecords = numbersFrom(lines[1])
    return Races(raceLengths.mapIndexed { index, length ->
        Race(length, raceRecords[index]) })
}

private fun numbersFrom(line: String) =
    Regex("(\\d+)")
        .findAll(line.replace(" ", ""))
        .map { it.value.toLong() }
        .toList()

class Races(private val races: List<Race>) {
    fun recordFactor() = races.fold(1, { accumulator, race ->
        accumulator * race.numberOfWaysYouCouldWin()})
}

class Race(val length: Long, val record: Long) {
    fun numberOfWaysYouCouldWin() = LongRange(1, length - 1)
        .fold(0, { accumulator, timeToHoldDownButton -> accumulator +
                if(((length - timeToHoldDownButton) * timeToHoldDownButton) > record) 1 else 0 })
}
