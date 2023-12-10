package com.oocode

fun pipesGridFrom(input: String): PipesGrid {
    val lines = input.split("\n")
    val tiles = lines.mapIndexed { y, line ->
        tilesFrom(y, line)
    }
    return PipesGrid(tiles)
}

data class PipesGrid(val tiles: List<List<Tile>>) {
    fun furthestDistanceFromStart(): Int {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return tiles.map { it.map { it.type.toString() }.joinToString("") }.joinToString("\n")
    }
}

enum class Compass { North, South, East, West }
interface TileType
data class ConnectingTileType(val representation: String, val outgoing1: Compass, val outgoing2: Compass) : TileType {
    override fun toString() = representation
}
class Ground : TileType {
    override fun toString() = "."
}

class Start : TileType {
    override fun toString() = "S"
}

val NE = ConnectingTileType("L", Compass.North, Compass.East)
val NW = ConnectingTileType("J", Compass.North, Compass.West)
val NS = ConnectingTileType("|", Compass.North, Compass.South)
val EW = ConnectingTileType("-", Compass.East, Compass.West)
val SE = ConnectingTileType("F", Compass.South, Compass.East)
val SW = ConnectingTileType("7", Compass.South, Compass.West)

val GROUND = Ground()
val START = Start()

data class Position(val x: Int, val y: Int)

data class Tile(val type: TileType, val position: Position)

fun tilesFrom(y: Int, line: String): List<Tile> = line.mapIndexed { x, c ->
    tileFor(c.toString(), x, y)
}

fun tileFor(name: String, x: Int, y: Int) = Tile(tileTypeFor(name), Position(x, y))

fun tileTypeFor(name: String) = when (name) {
    "|" -> NS
    "-" -> EW
    "F" -> SE
    "7" -> SW
    "L" -> NE
    "J" -> NW
    "." -> GROUND
    "S" -> START
    else -> {
        throw RuntimeException("Unexpected name: $name")
    }
}
