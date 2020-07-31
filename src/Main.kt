@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

import java.io.File

fun main() {
    listOf(File("input/input.txt")).forEach { input ->
        input.bufferedReader().useWith {
            val n = readLine().toInt()
            val numbers = readLines().map { it.split(" ") }.filter { it.size > 1 }.map { it.map { it.toInt() } }
            val output = mutableListOf<List<Int>>()

            numbers.forEach { workers ->
                val maxScore = workers.filter { it > 0 }.sum()
                val taken = mutableListOf<Int>()
                val other = mutableListOf<Int>()
                for (score in workers) {
                    if (score < maxScore && (other + score).sum() < maxScore && (other + score).sum() > other.sum()) {
                        other += score
                        taken += 1
                    } else {
                        taken += 0
                    }
                }
                output += listOf(maxScore)
                output += taken
            }

            println(output.joinToString("\n"))
        }
    }
}
