package org.danwatt.aoc22

class Day6 : AocDay<Int> {
    override fun part1(input: List<String>): Int = findMarker(input, 4)
    override fun part2(input: List<String>): Int = findMarker(input, 14)

    private fun findMarker(input: List<String>, distinctCharacters: Int): Int =
        distinctCharacters +
                input[0].windowed(distinctCharacters, 1, false) { it.toSet() }
                    .indexOfFirst { it.size == distinctCharacters }

}