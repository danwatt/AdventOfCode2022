package org.danwatt.aoc22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day2Test {

    val testLines = """A Y
            |B X
            |C Z""".trimMargin().lines()
    val lines = loadLines("day2.txt")
    val day = Day2()

    @Test
    fun testCase1() {
        assertThat(day.part1(testLines)).isEqualTo(15)
    }

    @Test
    fun part1() {
        assertThat(day.part1(lines)).isEqualTo(15337)
    }

    @Test
    fun testCase2() {
        assertThat(day.part2(testLines)).isEqualTo(12)
    }

    @Test
    fun part2() {
        assertThat(day.part2(lines)).isEqualTo(11696)
    }

}