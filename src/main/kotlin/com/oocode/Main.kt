import com.oocode.camelCardHandsFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(camelCardHandsFrom(File(args[0]).readText(UTF_8)).totalWinnings())
}
