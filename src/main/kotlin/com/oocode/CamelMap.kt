package com.oocode

fun directionsFrom(input: String): CamelMap {
    val lines = input.split("\n")
    val instructions = lines[0]
        .map { if (it == 'L') Choice.Left else Choice.Right }
    val nodes = lines.drop(2).map { nodeFrom(it) }
    return CamelMap(instructions, nodes)
}

enum class Choice { Left, Right }

data class CamelMap(
    private val instructions: List<Choice>,
    private val nodes: List<Node>
) {
    fun numberOfSteps(): Int {
        TODO()
    }
}

fun nodeFrom(line: String): Node = line
    .replace("(", "")
    .replace(")", "")
    .split("=")
    .let {
        val name = it[0].trim()
        val children = it[1].split(",")
        return Node(name, Children(children[0].trim(), children[1].trim()))
    }

data class Children(val left: String, val right: String)
data class Node(val name: String, val children: Children)
