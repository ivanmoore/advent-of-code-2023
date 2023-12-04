package com.oocode

fun engineSchematicFrom(input: String) : EngineSchematic {
    val lines = input.split("\n")
    val numbers = lines.flatMapIndexed { index, line -> numbersFrom(line, index) }.toSet()
    val symbols = lines.flatMapIndexed { index, line -> symbolsFrom(line, index) }.toSet()
    return EngineSchematic(numbers, symbols)
}

private fun Number.isNextToASymbol(symbols: Set<Symbol>) = symbols.any { symbol -> symbol.isNextTo(this) }

private fun Symbol.isNextTo(number: Number) = number.positions().any { position -> this.isNextTo(position)}
private fun Symbol.isNextTo(position: Pair<Int, Int>) = this.position.let { symbPos ->
    Math.abs(symbPos.first - position.first) <= 1 && Math.abs(symbPos.second - position.second) <= 1
}

private fun numbersFrom(line: String, y: Int) =
    Regex("(\\d+)")
        .findAll(line)
        .map { Number(Pair(it.range.first, y), it.value) }
        .toSet()

private fun symbolsFrom(line: String, y: Int): Set<Symbol> =
    line.mapIndexedNotNull { x, c -> if(c.isSymbol()) Symbol(Pair(x, y)) else null }.toSet()

private fun Char.isSymbol() = !isDigit() && this != '.'

data class Number(val startPosition: Pair<Int, Int>, val value: String) {
    fun positions(): Set<Pair<Int, Int>> = value.mapIndexed { index, _ ->
        startPosition.copy(startPosition.first + index) }.toSet()
}

data class Symbol(val position: Pair<Int, Int>)

class EngineSchematic(private val numbers: Set<Number>, private val symbols: Set<Symbol>) {
    fun total() = partNumbers().sumOf { it.value.toInt() }

    private fun partNumbers() = numbers.filter { number -> number.isNextToASymbol(symbols) }.toSet()
}
