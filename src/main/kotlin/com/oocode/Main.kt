import com.oocode.engineSchematicFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(engineSchematicFrom(File(args[0]).readText(UTF_8)).gearRatiosTotal())
}
