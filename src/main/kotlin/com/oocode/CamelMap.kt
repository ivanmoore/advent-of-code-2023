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
    private val nodesByName = nodes.associateBy { it.name }

    fun numberOfSteps(): Long {
        val startNodes = nodes.filter { it.name.endsWith("A") }
        val numberOfStepsForEveryStartingNode = startNodes.map { numberOfSteps(it) }
        return findLCMOfListOfNumbers(numberOfStepsForEveryStartingNode)
    }

    private fun numberOfSteps(startNode: Node): Long {
        var currentNode = startNode
        var result = 0L
        while (true) {
            instructions.forEach { instruction ->
                if (currentNode.name.endsWith("Z"))
                    return result
                result++
                currentNode = nodesByName[currentNode.follow(instruction)]!!
            }
        }
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
data class Node(val name: String, val children: Children) {
    fun follow(instruction: Choice) =
        if(instruction==Choice.Left) children.left else children.right
}

// Copied from https://www.baeldung.com/kotlin/lcm (and then replaced Int with Long)
// because I'm not interested in this part of the solution.
// I would expect this to be a thing you could find in a library
fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}
fun findLCMOfListOfNumbers(numbers: List<Long>): Long {
    var result = numbers[0]
    for (i in 1 until numbers.size) {
        result = findLCM(result, numbers[i])
    }
    return result
}
