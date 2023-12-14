import com.oocode.pipesGridFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(pipesGridFrom(File(args[0]).readText(UTF_8)).furthestDistanceFromStart())
}
