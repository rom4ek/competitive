@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package test2

import useWith
import java.io.File
import kotlin.math.abs
import kotlin.math.sqrt

private data class Grid(val cells: List<List<Cell>>, val rows: Int, val cols: Int) {

    fun allCells(): List<Cell> = cells.flatten()

    fun cell(row: Int, col: Int) = cells[row][col]

    fun isWorldBorderCell(cell: Cell): Boolean {
        if (cell.row == 0 || cell.col == 0 || cell.row == rows || cell.col == cols) {
            return true
        }
        return false
    }

    fun getAdjacentCells(cell: Cell): List<Cell> {
        val adjacents = ArrayList<Cell>(4)
        if (cell.row - 1 >= 0) {
            adjacents += cells[cell.row - 1][cell.col]
        }
        if (cell.row + 1 <= rows) {
            adjacents += cells[cell.row + 1][cell.col]
        }
        if (cell.col - 1 >= 0) {
            adjacents += cells[cell.row][cell.col - 1]
        }
        if (cell.col + 1 <= cols) {
            adjacents += cells[cell.row][cell.col + 1]
        }
        return adjacents
    }
}

private data class Cell(val row: Int, val col: Int, val altitude: Int, val country: Int) {
    fun distanceTo(other: Cell): Double {
        val deltaCol = abs(col - other.col).toDouble()
        val deltaRow = abs(row - other.row).toDouble()
        val distance = sqrt(deltaCol * deltaCol + deltaRow * deltaRow)
        return distance
    }

    fun isBorder(grid: Grid): Boolean {
        if (grid.isWorldBorderCell(this)) {
            return true
        }
        val cellsAround = grid.getAdjacentCells(this)
        return cellsAround.any { it.country != this.country }
    }
}

fun main() {
    File("input").listFiles().forEach { input ->
        input.bufferedReader().useWith {
            val (rows, clmns) = readLine().split(" ").map { it.toInt() }

            val cellRows = mutableListOf<MutableList<Cell>>()
            for (i in 0 until rows) {
                val row = readLine().split(" ").map { it.toInt() }
                val cellCols = mutableListOf<Cell>()
                for (j in 0 until clmns) {
                    val cell = Cell(i, j, row[j * 2], row[j * 2 + 1])
                    cellCols += cell
                }
                cellRows += cellCols
            }


            val grid = Grid(cellRows, rows - 1, clmns - 1)
            val countries = grid.allCells().groupBy { it.country }.toSortedMap()

            File("output/${input.nameWithoutExtension}.out").printWriter().useWith {

                countries.forEach {
                    val centerRow = it.value.sumBy { it.row }.toDouble() / it.value.size
                    val centerCol = it.value.sumBy { it.col }.toDouble() / it.value.size
                    var capital = grid.cell(centerRow.toInt(), centerCol.toInt())

                    if (capital.isBorder(grid) || capital.country != it.key) {
                        val candidates = it.value.filter { !it.isBorder(grid) }
                        capital = candidates.minBy { it.distanceTo(capital) }!!
                    }
                    println("${capital.col} ${capital.row}")
                }
            }
//            val sorted = grid.allCells().filter { it.isBorder(grid) }.groupBy { it.country }.toSortedMap()

//            println(allrows.joinToString("\n"))


        }
    }
}
