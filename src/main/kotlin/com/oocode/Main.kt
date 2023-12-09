import com.oocode.oasisReportFrom
import java.io.File
import kotlin.text.Charsets.UTF_8

fun main(args: Array<String>) {
    println(oasisReportFrom(File(args[0]).readText(UTF_8)).extrapolatedValuesSum())
}
