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
        return this.type().compareTo(that.type())
    }

    private fun type() =
        if (cards.toSet().size == 1) Type.FIVE_OF_A_KIND else Type.HIGH_CARD
}

fun camelCardHandFrom(s: String) = CamelCardHand(s)
