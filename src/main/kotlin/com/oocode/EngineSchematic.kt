package com.oocode

fun engineSchematicFrom(input: String): EngineSchematic {
    val lines = input.split("\n")
    val numbers = lines.flatMapIndexed { index, line -> numbersFrom(line, index) }.toSet()
    val symbols = lines.flatMapIndexed { index, line -> symbolsFrom(line, index) }.toSet()
    val gearIndicators = lines.flatMapIndexed { index, line -> gearIndicatorsFrom(line, index) }.toSet()
    return EngineSchematic(numbers, symbols, gearIndicators)
}

private fun Number.isNextToASymbol(symbols: Set<Symbol>) = symbols.any { symbol -> isNextTo(symbol.position) }

private fun Pair<Int, Int>.isNextTo(position: Pair<Int, Int>) =
    Math.abs(first - position.first) <= 1 && Math.abs(second - position.second) <= 1

private fun numbersFrom(line: String, y: Int) =
    Regex("(\\d+)")
        .findAll(line)
        .map { Number(Pair(it.range.first, y), it.value) }
        .toSet()

private fun symbolsFrom(line: String, y: Int): Set<Symbol> =
    line.mapIndexedNotNull { x, c -> if (c.isSymbol()) Symbol(Pair(x, y)) else null }.toSet()

private fun gearIndicatorsFrom(line: String, y: Int): Set<GearIndicator> =
    line.mapIndexedNotNull { x, c -> if (c.isGearIndicator()) GearIndicator(Pair(x, y)) else null }.toSet()

private fun Char.isSymbol() = !isDigit() && this != '.'

private fun Char.isGearIndicator() = this == '*'

data class Number(val startPosition: Pair<Int, Int>, val value: String) {
    fun positions(): Set<Pair<Int, Int>> = value.mapIndexed { index, _ ->
        startPosition.copy(startPosition.first + index)
    }.toSet()

    fun isNextTo(position: Pair<Int, Int>) = positions().any { it.isNextTo(position) }
}

data class Symbol(val position: Pair<Int, Int>)
data class GearIndicator(val position: Pair<Int, Int>)

class EngineSchematic(
    private val numbers: Set<Number>,
    private val symbols: Set<Symbol>,
    private val gearIndicators: Set<GearIndicator>
) {
    fun total() = partNumbers().sumOf { it.value.toInt() }

    private fun partNumbers() = numbers.filter { number -> number.isNextToASymbol(symbols) }.toSet()
    fun gearRatiosTotal() =
        gearIndicators
            .map { gearIndicator ->
                numbers
                    .filter { number -> number.isNextTo(gearIndicator.position) }
                    .map { it.value.toInt() }
            }
            .filter { it.size == 2 }
            .sumOf { it[0] * it[1] }
}
