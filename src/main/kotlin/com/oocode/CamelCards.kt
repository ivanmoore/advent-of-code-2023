package com.oocode

fun camelCardHandsFrom(input: String): CamelCardHands = TODO()

class CamelCardHands {
    fun totalWinnings(): Int {
        TODO("Not yet implemented")
    }
}

data class CamelCardHand(val cards: String) {
    enum class Type {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
    }

    operator fun compareTo(that: CamelCardHand): Int {
        if (this.type() == that.type()) {
            return this.cards.compareTo(that.cards)
        }
        return this.type().compareTo(that.type())
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
