package org.danwatt.aoc22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {
    val testLines = """vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw""".trimMargin().lines()
    val lines = loadLines("day3.txt")
    val day = Day3()

    @Test
    fun testCase1() {
        assertThat(day.part1(testLines)).isEqualTo(157)
    }

    @Test
    fun part1() {
        assertThat(day.part1(lines)).isEqualTo(8243)
    }

    @Test
    fun testCase2() {
        assertThat(day.part2(testLines)).isEqualTo(70)
    }

    @Test
    fun part2() {
        assertThat(day.part2(lines)).isEqualTo(132)
    }
}