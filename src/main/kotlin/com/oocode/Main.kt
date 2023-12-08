import com.oocode.directionsFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(directionsFrom(File(args[0]).readText(UTF_8)).numberOfSteps())
}
