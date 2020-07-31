@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package test3

import useWith
import java.awt.geom.Line2D
import java.awt.geom.Rectangle2D
import java.io.File
import kotlin.math.abs

private data class Ray(val originX: Int, val originY: Int, val dirX: Int, val dirY: Int) {

    fun isHorizontal(): Boolean {
        return abs(dirX) >= abs(dirY)
    }
}


private data class Cell(val x: Int, val y: Int) {

    fun instersects(ray: Ray): Boolean {
        val rect = Rectangle2D.Double(x - 0.5, y - 0.5, 1.0, 1.0)
//        val lines = listOf<Line2D>(
//            Line2D.Double(x - 0.5, x - 0.5, y - 0.5, y + 0.5),
//            Line2D.Double(x - 0.5, x + 0.5, y + 0.5, y + 0.5),
//            Line2D.Double(x + 0.5, x + 0.5, y + 0.5, y - 0.5),
//            Line2D.Double(x - 0.5, x + 0.5, y - 0.5, y - 0.5)
//        )

        val rayLine = Line2D.Double(
            ray.originX.toDouble(), ray.originY.toDouble(),
            ((ray.originX.toDouble() + ray.dirX)).toDouble(), ((ray.originY.toDouble() + ray.dirY)).toDouble()
        )

        return rayLine.intersects(rect)
    }
}

fun main() {
    File("input").listFiles().forEach { input ->
        input.bufferedReader().useWith {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            val numQueries = readLine().toInt()

            val rays = mutableListOf<Ray>()
            for (i in 0 until numQueries) {
                val rayVals = readLine().split(" ").map { it.toInt() }.toList()
                val ray = Ray(rayVals[0], rayVals[1], rayVals[2], rayVals[3])
                rays += ray
            }

            File("output/${input.nameWithoutExtension}.out").printWriter().useWith {
                rays.forEach { ray ->
                    val hitCells = mutableListOf<Cell>()
                    for (i in 0 until x) {
                        for (j in 0 until y) {
                            val cell = Cell(i, j)
                            if (cell.instersects(ray)) {
                                hitCells += cell
                            }
                        }
                    }
                    hitCells.sortBy { if (ray.isHorizontal()) it.x else it.y }
                    if (ray.isHorizontal() && ray.dirX < 0) {
                        hitCells.reverse()
                    } else if (!ray.isHorizontal() && ray.dirY < 0) {
                        hitCells.reverse()
                    }
                    println(hitCells.map { it.x.toString() + " " + it.y.toString()  }.joinToString(" "))
                }
            }


        }
    }
}
