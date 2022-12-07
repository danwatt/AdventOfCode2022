package org.danwatt.aoc22

import java.util.Stack

data class Move(val count: Int, val from: Int, val to: Int)

class Day5 : AocDay<String> {
    override fun part1(input: List<String>): String {
        val stacks = parseStacks(input)
        parseMoves(input).forEach { move ->
            (0 until move.count).map {
                stacks[move.to].push(stacks[move.from].pop())
            }
        }

        return stacks.map { it.peek() }.joinToString("")
    }

    override fun part2(input: List<String>): String {
        val stacks = parseStacks(input)
        parseMoves(input).forEach { move ->
            (0 until move.count).map {
                stacks[move.from].pop()
            }.reversed().let { temp ->
                temp.forEach { stacks[move.to].push(it) }
            }
        }

        return stacks.map { it.peek() }.joinToString("")
    }

    private fun parseMoves(input: List<String>): List<Move> =
        input.filter { it.startsWith("move") }
            .map { line ->
                line.split(' ')
                    .filterIndexed { index, _ -> index % 2 == 1 }
                    .map { it.toInt() }
            }.map { Move(it[0], it[1] - 1, it[2] - 1) }

    private fun parseStacks(input: List<String>): List<Stack<Char>> {
        val digitLine = input.indexOfFirst { it.contains('1') }
        val numberOfStacks = (input[digitLine].length / 4) + 1
        val stacks = List(numberOfStacks) { Stack<Char>() }
        input.subList(0, digitLine)
            .reversed()
            .forEach { line ->
                val paddedLine = line.padEnd(numberOfStacks * 4, ' ')
                (0 until numberOfStacks).forEach { stackNumber ->
                    val v = paddedLine[1 + (4 * stackNumber)]
                    if (v in 'A'..'Z') {
                        stacks[stackNumber].push(v)
                    }
                }
            }
        return stacks
    }
}