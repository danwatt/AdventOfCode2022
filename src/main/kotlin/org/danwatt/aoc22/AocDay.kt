package org.danwatt.aoc22

interface AocDay <T> {
    fun part1(input: List<String>): T
    fun part2(input: List<String>): T
}