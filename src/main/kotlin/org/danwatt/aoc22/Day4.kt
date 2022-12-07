package org.danwatt.aoc22

class Day4 : AocDay<Int> {
    override fun part1(input: List<String>): Int = input.count { line ->
        val (range1, range2) = line.split(",").map { it.toRange() }
        range1.all { range2.contains(it) } || range2.all { range1.contains(it) }
    }

    override fun part2(input: List<String>): Int = input.count { line ->
        val (range1, range2) = line.split(",").map { it.toRange() }
        (range1 intersect range2).isNotEmpty()
    }
}

private fun String.toRange(): IntRange {
    val (min, max) = this.split('-').map { it.toInt() }
    return min..max
}
