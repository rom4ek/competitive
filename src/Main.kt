import java.io.File

fun main() {
    File("input/1.txt").bufferedReader().useWith {
        val str = readLine()
        val str2 = readLine()
        File("output/1.txt").printWriter().useWith {
            println(str)
        }
        File("output/2.txt").printWriter().useWith {
            println(str2)
        }
    }
}
