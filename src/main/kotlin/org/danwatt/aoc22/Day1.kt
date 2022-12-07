package org.danwatt.aoc22

@JvmInline
value class Calories(val c: Int)

data class Elf(private val inventory: List<Calories>) {
    val total get() = inventory.sumOf { it.c }
}

class Day1 : AocDay<Int> {
    override fun part1(input: List<String>): Int =
        input.chunkedBy { it.isBlank() }
            .map { group -> group.map { Calories(it.toInt()) } }
            .map(::Elf)
            .maxBy { it.total }
            .total

    override fun part2(input: List<String>): Int =
        input.chunkedBy { it.isBlank() }
            .asSequence()
            .map { group -> group.map { Calories(it.toInt()) } }
            .map(::Elf)
            .sortedByDescending { it.total }
            .take(3)
            .sumOf { it.total }
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