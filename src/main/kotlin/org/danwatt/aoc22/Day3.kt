package org.danwatt.aoc22

class Day3 : AocDay<Int> {
    override fun part1(input: List<String>): Int =
        input.sumOf { rucksack ->
            val compartment1 = rucksack.substring(0, rucksack.length / 2).toSet()
            val compartment2 = rucksack.substring(rucksack.length / 2).toSet()
            (compartment1 intersect compartment2).first().toPriority()
        }

    override fun part2(input: List<String>): Int =
        input.chunked(3).sumOf { chunk ->
            chunk.map { it.toSet() }
                .reduce { acc, s -> acc intersect s }
                .first()
                .toPriority()
        }
}

private fun Char.toPriority(): Int =
    when (this) {
        in 'A'..'Z' -> this.code - 'A'.code + 27
        else -> this.code - 'a'.code + 1
    }
