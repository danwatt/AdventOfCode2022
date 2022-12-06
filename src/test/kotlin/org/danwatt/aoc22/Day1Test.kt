package org.danwatt.aoc22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day1Test {
    val input = loadLines("day1.txt")
    val day = Day1()

    @Test
    fun part1() {
        assertThat(day.part1(input)).isEqualTo(70369)
    }

    @Test
    fun part2() {
        assertThat(day.part2(input)).isEqualTo(203002)
    }
}

