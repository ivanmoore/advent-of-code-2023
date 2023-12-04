import com.oocode.scratchCardScoreFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(scratchCardScoreFrom(File(args[0]).readText(UTF_8)))
}
