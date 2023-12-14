package com.oocode

fun pipesGridFrom(input: String): PipesGrid {
    val lines = input.split("\n")
    val tiles = lines.mapIndexed { y, line ->
        tilesFrom(y, line)
    }
    return PipesGrid(tiles)
}

data class PipesGrid(val tiles: List<List<Tile>>) {
    fun furthestDistanceFromStart() = path().size / 2

    fun path(): MutableList<Tile> {
        val path = mutableListOf<Tile>()
        var current: Tile? = start()
        while (current != null) {
            path.add(current)
            current = nextTile(current, path)
        }
        return path
    }

    fun start() = tiles.flatten().first { it.type == START }

    fun nextTile(current: Tile, path: List<Tile>): Tile? = neighbouringTiles(current.position)
        .filter { !path.contains(it.tile) }
        .filter { it.canBeReachedFrom(it.directionTowardsThis.opposite) }
        .filter { current.canBeReachedFrom(it.directionTowardsThis) }
        .map { it.tile }
        .firstOrNull()

    fun neighbouringTiles(sourcePosition: Position) = neighbouringPositions(sourcePosition)
        .map { NeighbouringTile(it.outwardDirection, tileAt(it.position)) }

    private fun tileAt(position: Position): Tile = tiles[position.y][position.x]

    override fun toString(): String = tiles
        .map { it.map { it.type.toString() }.joinToString("") }
        .joinToString("\n")

    private fun neighbouringPositions(position: Position): Set<NeighbouringPosition> =
        Compass.values()
            .map { NeighbouringPosition(position + it.relativePosition, it) }
            .filter { isInBounds(it) }
            .toSet()

    private fun isInBounds(neighbouringPosition: NeighbouringPosition) = neighbouringPosition.position.let { position ->
        position.x >= 0 && position.y >= 0 && position.x < width && position.y < height
    }

    val width: Int get() = tiles[0].size
    val height: Int get() = tiles.size
}

enum class Compass(val relativePosition: Position) {
    North(Position(0, -1)),
    South(Position(0, 1)),
    East(Position(1, 0)),
    West(Position(-1, 0));

    val opposite: Compass get() = when(this) {
        North -> South
        South -> North
        East -> West
        West -> East
    }
}
interface TileType {
    fun canBeReachedFrom(positionRelativeToMe: Compass): Boolean
}

data class ConnectingTileType(val representation: String, val outgoing1: Compass, val outgoing2: Compass) : TileType {
    override fun canBeReachedFrom(positionRelativeToMe: Compass) =
        outgoing1 == positionRelativeToMe || outgoing2 == positionRelativeToMe

    override fun toString() = representation
}
data class Ground(val representation: String) : TileType {
    override fun canBeReachedFrom(positionRelativeToMe: Compass) = false

    override fun toString() = representation
}

private data class NeighbouringPosition(val position: Position, val outwardDirection: Compass)
data class NeighbouringTile(val directionTowardsThis: Compass, val tile: Tile) {
    fun canBeReachedFrom(direction: Compass) = tile.canBeReachedFrom(direction)
}

data class Start(val representation: String) : TileType {
    override fun canBeReachedFrom(positionRelativeToMe: Compass) = true
    override fun toString() = representation
}

val NE = ConnectingTileType("L", Compass.North, Compass.East)
val NW = ConnectingTileType("J", Compass.North, Compass.West)
val NS = ConnectingTileType("|", Compass.North, Compass.South)
val EW = ConnectingTileType("-", Compass.East, Compass.West)
val SE = ConnectingTileType("F", Compass.South, Compass.East)
val SW = ConnectingTileType("7", Compass.South, Compass.West)

val GROUND = Ground(".")
val START = Start("S")

data class Position(val x: Int, val y: Int) {
    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y)
}

data class Tile(val type: TileType, val position: Position) {
    fun canBeReachedFrom(direction: Compass): Boolean = direction.let { type.canBeReachedFrom(it) }
}

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
