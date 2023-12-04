package com.oocode

fun scratchCardScoreFrom(input: String): Int =
    input.split("\n").mapIndexed { index, line -> scratchCardComparisonFrom(index + 1, line).score() }.sum()

fun scratchCardsFrom(input: String): Map<Int, List<ScratchCardComparison>> {
    val scratchCardComparisons =
        input.split("\n").mapIndexed { index, line -> scratchCardComparisonFrom(index + 1, line) }
    val scratchCardComparisonsByCardNumber = scratchCardComparisons.associateBy { it.cardNumber }

    val withCopies = mutableListOf<ScratchCardComparison>()
    IntRange(1, scratchCardComparisons.size).forEach {
        iterateWithCopies(it, scratchCardComparisonsByCardNumber) { scratchCard ->
            withCopies.add(scratchCard)
        }
    }
    return withCopies.groupBy { it.cardNumber }
}

fun scratchCardNumberFrom(input: String) = scratchCardsFrom(input).values.sumOf { it.size }

fun iterateWithCopies(
    cardNumber: Int,
    scratchCards: Map<Int, ScratchCardComparison>,
    f: (scratchCard: ScratchCardComparison) -> Any
) {
    if (cardNumber > scratchCards.size) return
    val head = scratchCards[cardNumber]!!
    f(head)
    val rangeOfCardsToCopy = IntRange(cardNumber + 1, cardNumber + head.numberOfCorrectGuesses())
    rangeOfCardsToCopy.forEach {
        iterateWithCopies(it, scratchCards, f)
    }
}

fun scratchCardComparisonFrom(cardNumber: Int, line: String): ScratchCardComparison =
    line.split(":")[1].split("|").let {
        val winningNumbers = numbersFrom(it[0])
        val guesses = numbersFrom(it[1])
        return ScratchCardComparison(cardNumber, winningNumbers, guesses)
    }

private fun numbersFrom(s: String) =
    s.trim().split(Regex("\\s+"))
        .map { it.toInt() }
        .toSet()

data class ScratchCardComparison(
    val cardNumber: Int,
    private val winningNumbers: Set<Int>,
    private val guesses: Set<Int>
) {
    fun score() = numberOfCorrectGuesses().let {
        if (it == 0) 0 else twoToPowerOf(it - 1)
    }

    fun numberOfCorrectGuesses() = winningNumbers.intersect(guesses).size
}

fun twoToPowerOf(i: Int) = IntRange(0, i - 1)
    .fold(1, { accumlator, _ -> accumlator * 2 })
