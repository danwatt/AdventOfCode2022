package org.danwatt.aoc22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

typealias Calories = Int

data class Elf(private val inventory: List<Calories>) {
    val total get() = inventory.sumOf { it }
}

class Day1 {
    val elves =
        loadLines("day1.txt")
            .chunkedBy { it.isBlank() }
            .map { group -> group.map { it.toInt() } }
            .map(::Elf)

    @Test
    fun part1() {
        val most = elves.maxBy { it.total }
        assertThat(most.total).isEqualTo(70369)
    }

    @Test
    fun part2() {
        val totalTop3 = elves
            .sortedByDescending { it.total }
            .take(3)
            .sumOf { it.total }
        assertThat(totalTop3).isEqualTo(203002)
    }
}

private fun <T> Iterable<T>.chunkedBy(delimiterPredicate: (T) -> Boolean): Iterable<List<T>> {
    val iter = this.iterator()
    return Iterable {
        iterator {
            var accumulator = mutableListOf<T>()
            iter.forEach {
                if (delimiterPredicate(it)) {
                    if (accumulator.isNotEmpty()) {
                        yield(accumulator)
                    }
                    accumulator = mutableListOf()
                } else {
                    accumulator.add(it)
                }
            }
            if (accumulator.isNotEmpty()) {
                yield(accumulator)
            }
        }
    }
}