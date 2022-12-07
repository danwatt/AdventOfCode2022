package org.danwatt.aoc22

import java.util.Stack

sealed class FilesystemEntry

class File(val size: Int) : FilesystemEntry() {}
class Directory(
    val entries: MutableMap<String, FilesystemEntry> = mutableMapOf(),
) : FilesystemEntry() {
    fun totalSize(): Int = entries.values.sumOf {
        when (it) {
            is File -> it.size
            is Directory -> it.totalSize()
        }
    }

    fun flattenDirectories(): List<Directory> =
        entries.values.filterIsInstance<Directory>()
            .flatMap { it.flattenDirectories() } + this
}


class Day7 : AocDay<Int> {

    val totalSpace = 70_000_000
    val targetSpace = 30_000_000
    override fun part1(input: List<String>): Int {
        return parseDirectoryStructure(input)
            .flattenDirectories()
            .asSequence()
            .map { it.totalSize() }
            .filter { it <= 100000 }
            .sortedByDescending { it }
            .sum()
    }


    override fun part2(input: List<String>): Int {
        val root = parseDirectoryStructure(input)
        val totalUsed = root.totalSize()
        return root
            .flattenDirectories()
            .asSequence()
            .map { it.totalSize() }
            .sortedBy { it }
            .first { it + (totalSpace - totalUsed) >= targetSpace }
    }

    private fun parseDirectoryStructure(input: List<String>): Directory {
        val root = Directory()
        val stack = Stack<Directory>()
        input.forEach { line ->
            if (line.startsWith("$ cd ")) {
                when (val dir = line.substringAfter(" cd ")) {
                    "/" -> {
                        stack.clear()
                        stack.push(root)
                    }
                    ".." -> stack.pop()
                    else -> stack.push(stack.peek().entries[dir] as Directory)
                }
            } else if (line == "$ ls") {
                // ignore
            } else {
                // we are inside a ls
                if (line.startsWith("dir ")) {
                    stack.peek().entries[line.substringAfter(' ')] = Directory()
                } else {
                    val size = line.substringBefore(' ').toInt()
                    val name = line.substringAfter(' ')
                    stack.peek().entries[name] = File(size)
                }
            }
        }
        return root
    }
}