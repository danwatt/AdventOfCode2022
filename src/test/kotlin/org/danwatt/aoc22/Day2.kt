package org.danwatt.aoc22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

enum class Outcome {
    Loss,
    Draw,
    Win
}

enum class RPS(val points: Int) {
    Rock(1),
    Paper(2),
    Scissors(3);

    companion object {
        private val beat = mapOf(
            Rock to Scissors,
            Paper to Rock,
            Scissors to Paper
        )
        private val loss = beat.entries.associateBy({ it.value }) { it.key }
    }

    fun beats(theirs: RPS): Boolean = beat[this] == theirs

    fun determineOurPlay(outcome: Outcome): RPS = when (outcome) {
        Outcome.Draw -> this
        Outcome.Win -> loss[this]!!
        Outcome.Loss -> beat[this]!!
    }
}


private fun scorePart1(lines: List<String>): Int =
    lines.map { it.parsePart1Scenario() }
        .sumOf { (theirPlay, ourPlay) ->
            ourPlay.points + when {
                theirPlay == ourPlay -> 3
                theirPlay.beats(ourPlay) -> 0
                else -> 6
            }
        }

private fun scorePart2(lines: List<String>): Int =
    lines.map { it.parsePart2Scenario() }
        .sumOf { (theirPlay, desiredOutcome) ->
            val ourPlay = theirPlay.determineOurPlay(desiredOutcome)
            ourPlay.points + when (desiredOutcome) {
                Outcome.Win -> 6
                Outcome.Draw -> 3
                Outcome.Loss -> 0
            }
        }

private fun String.parsePart1Scenario(): Pair<RPS, RPS> {
    val theirs = when (this[0]) {
        'A' -> RPS.Rock
        'B' -> RPS.Paper
        'C' -> RPS.Scissors
        else -> throw IllegalArgumentException()
    }
    val ours = when (this[2]) {
        'X' -> RPS.Rock
        'Y' -> RPS.Paper
        'Z' -> RPS.Scissors
        else -> throw IllegalArgumentException()
    }
    return theirs to ours
}

private fun String.parsePart2Scenario(): Pair<RPS, Outcome> {
    val theirs = when (this[0]) {
        'A' -> RPS.Rock
        'B' -> RPS.Paper
        'C' -> RPS.Scissors
        else -> throw IllegalArgumentException()
    }
    val ours = when (this[2]) {
        'X' -> Outcome.Loss
        'Y' -> Outcome.Draw
        'Z' -> Outcome.Win
        else -> throw IllegalArgumentException()
    }
    return theirs to ours
}

class Day2 {

    @Test
    fun testCase1() {
        val lines = """A Y
            |B X
            |C Z""".trimMargin().lines()
        val score = scorePart1(lines)
        assertThat(score).isEqualTo(15)
    }


    @Test
    fun part1() {
        val lines = loadLines("day2.txt")
        val score = scorePart1(lines)
        assertThat(score).isEqualTo(15337)
    }

    @Test
    fun testCase2() {
        val lines = """A Y
            |B X
            |C Z""".trimMargin().lines()
        val score = scorePart2(lines)
        assertThat(score).isEqualTo(12)
    }

    @Test
    fun part2() {
        val lines = loadLines("day2.txt")
        val score = scorePart2(lines)
        assertThat(score).isEqualTo(15337)
    }

}