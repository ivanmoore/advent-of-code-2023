package com.oocode

fun scratchCardScoreFrom(input: String): Int =
    input.split("\n").sumOf { line -> scratchCardComparisonFrom(line).score() }

fun scratchCardComparisonFrom(line: String): ScratchCardComparison =
    line.split(":")[1].split("|").let {
        val winningNumbers = numbersFrom(it[0])
        val guesses = numbersFrom(it[1])
        return ScratchCardComparison(winningNumbers, guesses)
    }

private fun numbersFrom(s: String) =
    s.trim().split(Regex("\\s+"))
        .map { it.toInt() }
        .toSet()

data class ScratchCardComparison(
    private val winningNumbers: Set<Int>,
    private val guesses: Set<Int>
) {
    fun score() = numberOfCorrectGuesses().let {
        if (it == 0) 0 else twoToPowerOf(it - 1)
    }

    private fun numberOfCorrectGuesses() = winningNumbers.intersect(guesses).size
}

fun twoToPowerOf(i: Int) = IntRange(0, i - 1)
    .fold(1, { accumlator, _ -> accumlator * 2 })