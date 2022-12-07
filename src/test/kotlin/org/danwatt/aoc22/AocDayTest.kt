package org.danwatt.aoc22

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

abstract class AocDayTest(
    val day: AocDay,
    val testCasePart1: Int = 0,
    val actualPart1: Int = 0,
    val testCasePart2: Int = 0,
    val actualPart2: Int = 0,
) {

    fun loadLines(): List<String> = loadLines("${day.javaClass.simpleName.lowercase()}.txt")
    fun loadTestLines(): List<String> = loadLines("${day.javaClass.simpleName.lowercase()}-test.txt")

    @Test
    fun testCase1() {
        assertThat(day.part1(loadTestLines())).isEqualTo(testCasePart1)
    }

    @Test
    fun part1() {
        assertThat(day.part1(loadLines())).isEqualTo(actualPart1)
    }

    @Test
    fun testCase2() {
        assertThat(day.part2(loadTestLines())).isEqualTo(testCasePart2)
    }

    @Test
    fun part2() {
        assertThat(day.part2(loadLines())).isEqualTo(actualPart2)
    }
}