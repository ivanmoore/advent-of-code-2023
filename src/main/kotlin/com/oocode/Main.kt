import com.oocode.racesFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(racesFrom(File(args[0]).readText(UTF_8)).recordFactor())
}
