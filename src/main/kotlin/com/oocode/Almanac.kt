package com.oocode

fun almanacFrom(input: String): Almanac {
    TODO("Not yet implemented")
}

class Almanac {
    fun lowestLocationNumber(): Int {
        TODO("Not yet implemented")
    }
}

data class Mapping(
    private val destinationRangeStart: Int,
    private val sourceRangeStart: Int,
    private val rangeLength: Int,
) {
    private val sourceRange = IntRange(sourceRangeStart, sourceRangeStart + rangeLength - 1)
    fun find(sourceNumber: Int) =
        if (sourceRange.contains(sourceNumber))
            destinationRangeStart + (sourceNumber - sourceRangeStart)
        else
            null
}