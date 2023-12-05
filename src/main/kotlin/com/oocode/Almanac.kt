package com.oocode

fun almanacFrom(input: String): Almanac {
    val lines = input.split("\n")
    val seeds = lines[0].split(" ").drop(1).map { it.toLong() }

    val converters = mutableListOf<Converter>()
    var currentMappings = mutableListOf<Mapping>()
    lines.drop(1).forEach { line ->
        if (line.isEmpty()) {
            if(currentMappings.isNotEmpty()) converters.add(Converter(currentMappings))
            currentMappings = mutableListOf()
        } else {
            val numbers = numbersFrom(line)
            if (numbers.isNotEmpty()) {
                currentMappings.add(Mapping(numbers[0], numbers[1], numbers[2]))
            }
        }
    }
    if(currentMappings.isNotEmpty()) converters.add(Converter(currentMappings))

    return Almanac(seeds, ConverterChain(converters))
}

private fun numbersFrom(line: String) =
    Regex("(\\d+)")
        .findAll(line)
        .map { it.value.toLong() }
        .toList()

class Almanac(private val seeds: List<Long>, private val converterChain: ConverterChain) {
    fun lowestLocationNumber() = seeds.minOf { converterChain.convert(it) }
}

data class Mapping(
    private val destinationRangeStart: Long,
    private val sourceRangeStart: Long,
    private val rangeLength: Long,
) {
    private val sourceRange = LongRange(sourceRangeStart, sourceRangeStart + rangeLength - 1)

    fun find(sourceNumber: Long) =
        if (sourceRange.contains(sourceNumber))
            destinationRangeStart + (sourceNumber - sourceRangeStart)
        else
            null
}

class Converter(private val mappings: List<Mapping>) {
    fun convert(sourceNumber: Long) = mappings.firstNotNullOfOrNull { it.find(sourceNumber) } ?: sourceNumber
}

class ConverterChain(private val converters: List<Converter>) {
    fun convert(sourceNumber: Long) =
        converters.fold(sourceNumber, { accumulator, converter -> converter.convert(accumulator) })
}
