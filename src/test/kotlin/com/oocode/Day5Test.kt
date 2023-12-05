package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day5Test {
    @Disabled
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""
        assertThat(almanacFrom(input).lowestLocationNumber(), equalTo(35))
    }

    @Test
    fun calculatesCorrectMapping() {
        val mapping = Mapping(50, 98, 2)
        assertThat(mapping.find(97), equalTo(null))
        assertThat(mapping.find(98), equalTo(50))
        assertThat(mapping.find(99), equalTo(51))
        assertThat(mapping.find(100), equalTo(null))
    }
}
