@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

import java.io.File

fun main() {
    File("input").listFiles().forEach { input ->
        input.bufferedReader().useWith {
            val strings = readLines()

            File("output/${input.name}").printWriter().useWith { println(strings) }
        }
    }
}
