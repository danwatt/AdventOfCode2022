package org.danwatt.aoc22

class Day6 : AocDay<Int> {
    override fun part1(input: List<String>): Int = findMarker(input[0], 4)
    override fun part2(input: List<String>): Int = findMarker(input[0], 14)

    private fun findMarker(input: String, distinctCharacters: Int): Int =
        distinctCharacters + input.windowed(distinctCharacters) { it.toSet() }
            .indexOfFirst { it.size == distinctCharacters }

}