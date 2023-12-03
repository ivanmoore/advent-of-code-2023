package com.oocode

data class Bag(val red: Int, val green: Int, val blue: Int) {
    fun possibilityTotal(input: String) =
        input.split("\n").map { line -> line.possibilityValue(this) }.sum()
}

private fun String.possibilityValue(bag: Bag) = gameFrom(this).possibilityValue(bag)

data class Game(val number: Int, val reveals: Set<Reveal>) {
    fun possibilityValue(bag: Bag) = if (reveals.all { it.isPossibleGiven(bag) }) number else 0
}

data class Reveal(val red: Int = 0, val green: Int = 0, val blue: Int = 0) {
    fun isPossibleGiven(bag: Bag) = red <= bag.red && green <= bag.green && blue <= bag.blue
}

// "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
fun gameFrom(line: String): Game {
    val number = line.split(":")[0].split(" ")[1].toInt()
    val revealStrings = line.split(":")[1].split(";")
    return Game(number, revealStrings.map { revealFrom(it) }.toSet())
}

fun revealFrom(revealString: String): Reveal {
    val colorNumberPairs = revealString.split(",").map { asColorNumberPair(it.trim()) }.toMap()
    return Reveal(
        red = colorNumberPairs["red"] ?: 0,
        green = colorNumberPairs["green"] ?: 0,
        blue = colorNumberPairs["blue"] ?: 0,
    )
}

fun asColorNumberPair(colorNumberPairString: String): Pair<String, Int> =
    colorNumberPairString.split(" ")[1] to colorNumberPairString.split(" ")[0].toInt()
