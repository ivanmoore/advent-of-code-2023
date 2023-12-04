import com.oocode.powerOf
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(powerOf(File(args[0]).readText(UTF_8)))
}
