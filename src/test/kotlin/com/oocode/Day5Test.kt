package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class Day5Test {
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
        assertThat(almanacFrom(input).lowestLocationNumber(), equalTo(46))
    }

    @Test
    fun canParseReallyBigNumbers() {
        val input = """seeds: 3911195009 1

seed-to-soil map:
3911195109 3911195009 1

soil-to-fertilizer map:
0 15 37"""
        assertThat(almanacFrom(input).lowestLocationNumber(), equalTo(3911195109))
    }

    @Test
    fun calculatesCorrectMapping() {
        val mapping = Mapping(50, 98, 2)
        assertThat(mapping.find(97), equalTo(null))
        assertThat(mapping.find(98), equalTo(50))
        assertThat(mapping.find(99), equalTo(51))
        assertThat(mapping.find(100), equalTo(null))
    }

    @Test
    fun calculatesCorrectConversion() {
        val mapping1 = Mapping(50, 98, 2)
        val mapping2 = Mapping(52, 50, 48)
        val converter = Converter(listOf(mapping1, mapping2))

        assertThat(converter.convert(0), equalTo(0))
        assertThat(converter.convert(1), equalTo(1))

        assertThat(converter.convert(48), equalTo(48))
        assertThat(converter.convert(49), equalTo(49))
        assertThat(converter.convert(50), equalTo(52))
        assertThat(converter.convert(51), equalTo(53))

        assertThat(converter.convert(96), equalTo(98))
        assertThat(converter.convert(97), equalTo(99))
        assertThat(converter.convert(98), equalTo(50))
        assertThat(converter.convert(99), equalTo(51))
    }

    @Test
    fun calculatesCorrectConversionRanges() {
        val mapping1 = Mapping(50, 98, 2)  // 98-99 -> 50-51
        val mapping2 = Mapping(52, 50, 48) // 50-97 -> 52-99
        val converter = Converter(listOf(mapping1, mapping2))

        assertThat(
            converter.convert(sourceRange("40-49")),
            equalTo(setOf(sourceRange("40-49"))))
        assertThat(
            converter.convert(sourceRange("49-100")),
            equalTo(setOf(
                sourceRange("49-49"),
                sourceRange("52-99"),
                sourceRange("50-51"),
                sourceRange("100-100")
            )))
    }

    private fun sourceRange(rangeString: String): InputRange = rangeString.split("-").let {
        InputRange(it[0].toLong(), it[1].toLong())
    }

    @Test
    fun checkSourceRangeMethodForTesting() {
        assertThat(sourceRange("40-49"), equalTo(InputRange(40, 49)))
    }

    @Test
    fun canChainConverters() {
        val converter1 = Converter(listOf(
            Mapping(50, 98, 2),
            Mapping(52, 50, 48)))
        val converter2 = Converter(listOf(
            Mapping(0, 15, 37),
            Mapping(37, 52, 2),
            Mapping(39, 0, 15)))

        val converter = ConverterChain(listOf(converter1, converter2))
        assertThat(converter.convert(InputRange(79,79)), equalTo(setOf(InputRange(81,81))))
        assertThat(converter.convert(InputRange(14,14)), equalTo(setOf(InputRange(53,53))))
        assertThat(converter.convert(InputRange(55,55)), equalTo(setOf(InputRange(57,57))))
        assertThat(converter.convert(InputRange(13,13)), equalTo(setOf(InputRange(52,52))))
    }
}
