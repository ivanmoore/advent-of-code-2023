import com.oocode.Bag
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(Bag(red = 12, green = 13, blue = 14).possibilityTotal(File(args[0]).readText(UTF_8)))
}

fun day1(input: String) = input.split("\n").map { line -> line.calibrationValue() }.sum()

fun String.calibrationValue() = (firstDigit() * 10) + lastDigit()

private fun String.firstDigit() = digits().first().digitToInt()

private fun String.lastDigit() = digits().last().digitToInt()

val numberWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun String.digits() = IntRange(0, this.length - 1)
    .map { index ->
        this.digitifyStartingAt(index)
    }.joinToString("")

fun String.digitifyStartingAt(index: Int) =
    if (this[index].isDigit())
        this[index].toString()
    else numberStartingAt(index) ?: ""

fun String.numberStartingAt(index: Int) =
    numberWords
        .mapIndexed { wordIndex, word -> if (this.startsWith(word, index)) (wordIndex + 1).toString() else null }
        .filterNotNull()
        .firstOrNull()
