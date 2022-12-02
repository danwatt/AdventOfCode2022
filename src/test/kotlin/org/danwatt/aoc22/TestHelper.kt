package org.danwatt.aoc22

fun loadIntegers(file: String): List<Int> =
    Day1::class.java.classLoader.getResource(file)!!.readText().lines().map { it.toInt() }

fun loadIntegersWithEmptyLinesWithNullConversion(file: String, function: () -> Int): List<Int> =
    Day1::class.java.classLoader.getResource(file)!!
        .readText()
        .lines()
        .map {
            when (it) {
                "" -> function()
                else -> it.toInt()
            }
        }