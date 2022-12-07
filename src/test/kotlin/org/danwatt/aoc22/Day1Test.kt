package org.danwatt.aoc22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {
    val input = loadLines("day1.txt")
    val day = Day1()
    val testLines = """
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent().lines()

    @Test
    fun testCase1() {
        assertThat(day.part1(testLines)).isEqualTo(24000)
    }

    @Test
    fun part1() {
        assertThat(day.part1(input)).isEqualTo(70369)
    }

    @Test
    fun testCase2() {
        assertThat(day.part2(testLines)).isEqualTo(45000)

    }

    @Test
    fun part2() {
        assertThat(day.part2(input)).isEqualTo(203002)
    }
}

