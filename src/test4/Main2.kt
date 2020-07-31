@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package test4

import useWith
import java.io.File

data class Grid(val cells: List<Cell>, val rows: Int, val cols: Int) {
    fun isWorldBorderCell(cell: Cell): Boolean {
        if (cell.row == 0 || cell.col == 0 || cell.row == rows || cell.col == cols) {
            return true
        }
        return false
    }

    fun getAdjacentCells(cell: Cell): List<Cell> {
        return cells.filter {
            if (it.row == cell.row && it.col == cell.col - 1) {
                return@filter true
            }
            if (it.row == cell.row && it.col == cell.col + 1) {
                return@filter true
            }
            if (it.col == cell.col && it.row == cell.row - 1) {
                return@filter true
            }
            if (it.col == cell.col && it.row == cell.row + 1) {
                return@filter true
            }
            return@filter false
        }
    }
}

data class Cell(val row: Int, val col: Int, val altitude: Int, val country: Int) {
    fun isBorder(grid: Grid): Boolean {
        if (grid.isWorldBorderCell(this)) {
            return true
        }
        val cellsAround = grid.getAdjacentCells(this)
        return cellsAround.any { it.country != this.country }
    }
}

fun main() {
    listOf(File("input/level4_1.in.html")).forEach { input ->
        input.bufferedReader().useWith {
            val (rows, clmns) = readLine().split(" ").map { it.toInt() }

            val cells = mutableListOf<Cell>()
            for (i in 0 until rows) {
                val row = readLine().split(" ").map { it.toInt() }
                for (j in 0 until clmns) {
                    val cell = Cell(i, j, row[j * 2], row[j * 2 + 1])
                    print(cell.country.toString() + " ")
                    cells += cell
                }
                println("")
            }


//            val grid = Grid(cells, rows - 1, clmns - 1)
//            val sorted = cells.filter { it.isBorder(grid) }.groupBy { it.country }.toSortedMap()
//
//
////            println(allrows.joinToString("\n"))
//
//            File("output/output.out").printWriter().useWith {
//                sorted.forEach {
//                    println(it.value.size)
//                }
//            }
        }
    }
}
