package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class Day3Test {
    @Test
    fun calculatesCorrectAnswersForExample() {
        val input = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598.."""
        assertThat(engineSchematicFrom(input).total(), equalTo(4361))
    }
}
