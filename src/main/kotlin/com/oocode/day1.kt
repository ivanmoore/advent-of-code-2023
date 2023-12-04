package com.oocode

fun day1(input: String) = input.split("\n").sumOf { line -> line.calibrationValue() }

fun String.calibrationValue() = (firstDigit() * 10) + lastDigit()

private fun String.firstDigit() = digits().first().digitToInt()

private fun String.lastDigit() = digits().last().digitToInt()

val numberWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun String.digits() = IntRange(0, this.length - 1)
    .joinToString("") { index -> digitifyStartingAt(index) }

fun String.digitifyStartingAt(index: Int) =
    if (this[index].isDigit())
        this[index].toString()
    else numberStartingAt(index) ?: ""

fun String.numberStartingAt(index: Int) =
    numberWords
        .mapIndexed { wordIndex, word -> if (this.startsWith(word, index)) (wordIndex + 1).toString() else null }
        .filterNotNull()
        .firstOrNull()
