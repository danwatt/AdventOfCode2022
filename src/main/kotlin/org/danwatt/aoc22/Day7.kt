package org.danwatt.aoc22

import java.util.Stack

sealed class FilesystemEntry

class File(val size: Int) : FilesystemEntry() {}
class Directory(
    val children: MutableMap<String, FilesystemEntry> = mutableMapOf(),
    val size: Int = 0,
) : FilesystemEntry(), Iterable<FilesystemEntry> {
    fun totalSize(): Int = children.values.sumOf {
        when (it) {
            is File -> it.size
            is Directory -> it.totalSize()
        }
    }

    override fun iterator(): Iterator<FilesystemEntry> = iterator {
        yield(this@Directory);
        this@Directory.children.values.forEach {
            when (it) {
                is Directory -> yieldAll(it.iterator())
                else -> yield(it)
            }
        }
    }
}


class Day7 : AocDay<Int> {

    val totalSpace = 70_000_000
    val targetSpace = 30_000_000
    override fun part1(input: List<String>): Int =
        parseDirectoryStructure(input)
            .iterator()
            .asSequence()
            .filterIsInstance<Directory>()
            .map { it.totalSize() }
            .filter { it <= 100000 }
            .sortedByDescending { it }
            .sum()


    override fun part2(input: List<String>): Int {
        val root = parseDirectoryStructure(input)
        val totalUsed = root.totalSize()
        return root
            .iterator()
            .asSequence()
            .filterIsInstance<Directory>()
            .map { it.totalSize() }
            .sortedBy { it }
            .first { it + (totalSpace - totalUsed) >= targetSpace }
    }

    private fun parseDirectoryStructure(input: List<String>): Directory {
        val root = Directory()
        val stack = Stack<Directory>()
        input.forEach { line ->
            when {
                line.startsWith("$ cd ") -> {
                    when (val dir = line.substringAfter(" cd ")) {
                        "/" -> {
                            stack.clear()
                            stack.push(root)
                        }

                        ".." -> stack.pop()
                        else -> stack.push(stack.peek().children[dir] as Directory)
                    }
                }

                line.startsWith("$ ls") -> { /* Ignore */
                }

                line.startsWith("dir ") -> {
                    stack.peek().children[line.substringAfter(' ')] = Directory()
                }

                else -> {
                    val size = line.substringBefore(' ').toInt()
                    val name = line.substringAfter(' ')
                    stack.peek().children[name] = File(size)
                }
            }
        }
        return root
    }
}