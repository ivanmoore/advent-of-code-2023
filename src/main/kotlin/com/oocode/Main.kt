import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(q1(File(args[0]).readText(UTF_8)))
}

fun q1(input: String) = input.split("\n").map { line -> line.calibrationValue() }.sum()

fun String.calibrationValue() = (digits().first().digitToInt() * 10) + digits().last().digitToInt()

fun String.digits() = filter { it.isDigit() }
