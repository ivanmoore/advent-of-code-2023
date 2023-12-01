import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(day1(File(args[0]).readText(UTF_8)))
}

fun day1(input: String) = input.split("\n").map { line -> line.calibrationValue() }.sum()

fun String.calibrationValue() = (firstDigit() * 10) + lastDigit()

private fun String.firstDigit() = digits().first().digitToInt()

private fun String.lastDigit() = digits().last().digitToInt()

fun String.digits() = filter { it.isDigit() }
