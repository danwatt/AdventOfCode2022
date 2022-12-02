package org.danwatt.aoc22

fun loadLines(file: String): List<String> =
    Day1::class.java.classLoader.getResource(file)!!
        .readText()
        .lines()
