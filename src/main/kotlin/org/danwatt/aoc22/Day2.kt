package org.danwatt.aoc22

class Day2 : AocDay<Int> {
    override fun part1(input: List<String>): Int =
        input.map { it.parsePart1Scenario() }
            .sumOf { (theirPlay, ourPlay) ->
                ourPlay.points + when {
                    theirPlay == ourPlay -> 3
                    theirPlay.beats(ourPlay) -> 0
                    else -> 6
                }
            }

    override fun part2(input: List<String>): Int =
        input.map { it.parsePart2Scenario() }
            .sumOf { (theirPlay, desiredOutcome) ->
                val ourPlay = theirPlay.determineOurPlay(desiredOutcome)
                ourPlay.points + when (desiredOutcome) {
                    Outcome.Win -> 6
                    Outcome.Draw -> 3
                    Outcome.Loss -> 0
                }
            }
}

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