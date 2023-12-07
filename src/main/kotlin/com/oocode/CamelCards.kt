package com.oocode

fun camelCardHandsFrom(input: String): CamelCardHands =
    CamelCardHands(input.split("\n")
        .map { line -> line.split(" ").let { CamelCardHand(it[0]) to it[1].toInt() } })

class CamelCardHands(private val handsWithBids: List<Pair<CamelCardHand, Int>>) {
    fun totalWinnings(): Int {
        val sortedBy = handsWithBids.sortedBy { it.first }
        return sortedBy.foldIndexed(0, { index, accumulator, cardWithBid ->
            accumulator + (cardWithBid.second * (index + 1))
        })
    }
}

data class CamelCardHand(val cards: String) : Comparable<CamelCardHand> {
    enum class Type {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
    }

    private val labels = "AKQJT98765432".reversed()

    override operator fun compareTo(other: CamelCardHand): Int {
        if (this.type() == other.type()) {
            fun makeEasilyComparable(cards: String) =
                cards.map { labels.indexOf(it) }.map { 'a'.plus(it) }.toString()
            return makeEasilyComparable(this.cards).compareTo(makeEasilyComparable(other.cards))
        }
        return this.type().compareTo(other.type())
    }

    private fun type(): Type {
        val groupsOfSameCards = cards.groupBy { it }
        if (groupsOfSameCards.size == 1)
            return Type.FIVE_OF_A_KIND
        if (groupsOfSameCards.size == 2)
            if (groupsOfSameCards.map { it.value.size }.max() == 4)
                return Type.FOUR_OF_A_KIND
            else
                return Type.FULL_HOUSE
        if (groupsOfSameCards.size == 3)
            if (groupsOfSameCards.map { it.value.size }.max() == 3)
                return Type.THREE_OF_A_KIND
            else
                return Type.TWO_PAIR
        if (groupsOfSameCards.size == 4)
            return Type.ONE_PAIR
        else
            return Type.HIGH_CARD
    }
}

fun camelCardHandFrom(s: String) = CamelCardHand(s)
