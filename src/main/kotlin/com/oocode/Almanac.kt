package com.oocode

fun almanacFrom(input: String): Almanac {
    val lines = input.split("\n")
    val seedNumbers = lines[0].split(" ").drop(1).map { it.toLong() }
    val seeds = seedNumbers.chunked(2).map {
        val startNumber = it[0]
        val rangeSize = it[1]
        InputRange(startNumber, (startNumber + rangeSize) - 1)
    }

    val converters = mutableListOf<Converter>()
    var currentMappings = mutableListOf<Mapping>()
    lines.drop(1).forEach { line ->
        if (line.isEmpty()) {
            if (currentMappings.isNotEmpty()) converters.add(Converter(currentMappings))
            currentMappings = mutableListOf()
        } else {
            val numbers = numbersFrom(line)
            if (numbers.isNotEmpty()) {
                currentMappings.add(Mapping(numbers[0], numbers[1], numbers[2]))
            }
        }
    }
    if (currentMappings.isNotEmpty()) converters.add(Converter(currentMappings))

    return Almanac(seeds, ConverterChain(converters))
}

data class InputRange(val startNumber: Long, val endNumber: Long)

private fun numbersFrom(line: String) =
    Regex("(\\d+)")
        .findAll(line)
        .map { it.value.toLong() }
        .toList()

class Almanac(private val seeds: List<InputRange>, private val converterChain: ConverterChain) {
    fun lowestLocationNumber() = seeds.flatMap { converterChain.convert(it) }.map { it.startNumber }.min()
}

data class Mapping(
    private val destinationRangeStart: Long,
    private val sourceRangeStart: Long,
    private val rangeLength: Long,
) {
    val sourceRange = LongRange(sourceRangeStart, sourceRangeStart + rangeLength - 1)

    fun find(sourceNumber: Long) =
        if (sourceRange.contains(sourceNumber))
            destinationRangeStart + (sourceNumber - sourceRangeStart)
        else
            null
}

class Converter(private val mappings: List<Mapping>) {
    fun convert(sourceNumber: Long) = mappings.firstNotNullOfOrNull { it.find(sourceNumber) } ?: sourceNumber

    fun convert(inputRanges: Set<InputRange>): Set<InputRange> = inputRanges.flatMap { convert(it) }.toSet()

    fun convert(inputRange: InputRange): Set<InputRange> {
        val mappingsInOrder = overlappingMappings(inputRange)
        if (mappingsInOrder.isEmpty()) {
            return setOf(inputRange)
        }
        val firstMappingSourceRange = mappingsInOrder[0].sourceRange
        val firstMappingStart = firstMappingSourceRange.start
        if (inputRange.startNumber < firstMappingStart) {
            return setOf(inputRange.copy(endNumber = firstMappingStart - 1)) +
                    convert(inputRange.copy(startNumber = firstMappingStart))
        }
        if (inputRange.endNumber <= firstMappingSourceRange.endInclusive) {
            return mapped(inputRange)
        }
        return mapped(inputRange.copy(endNumber = firstMappingSourceRange.endInclusive)) +
                convert(inputRange.copy(startNumber = firstMappingSourceRange.endInclusive + 1))
    }

    private fun mapped(inputRange: InputRange): Set<InputRange> =
        setOf(InputRange(convert(inputRange.startNumber), convert(inputRange.endNumber)))

    private fun overlappingMappings(inputRange: InputRange) = mappings
        .sortedBy { it.sourceRange.first }
        .filter { it.sourceRange.overlapsWith(inputRange) }
}

private fun LongRange.overlapsWith(inputRange: InputRange) =
    !(inputRange.endNumber < start || inputRange.startNumber > endInclusive)

class ConverterChain(private val converters: List<Converter>) {
    fun convert(sourceNumber: InputRange) =
        converters.fold(setOf(sourceNumber), { accumulator, converter -> converter.convert(accumulator) })
}
