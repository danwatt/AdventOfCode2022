package org.danwatt.aoc22

fun loadLines(file: String): List<String> =
    AocDayTest::class.java.classLoader.getResource(file)!!
        .readText()
        .lines()
