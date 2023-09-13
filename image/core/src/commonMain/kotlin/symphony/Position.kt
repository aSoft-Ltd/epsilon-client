package symphony

data class Position(
    var x: Int,
    var y: Int
) {
    constructor(x: Number = 0, y: Number = 0) : this(x.toInt(), y.toInt())

    operator fun plus(other: Position) = Position(x + other.x, y + other.y)

    operator fun minus(other: Position) = Position(x - other.x, y - other.y)

    operator fun div(number: Number) = Position(
        x = x.toDouble() / number.toDouble(),
        y = y.toDouble() / number.toDouble()
    )

    operator fun times(number: Number) = Position(
        x = x.toDouble() * number.toDouble(),
        y = y.toDouble() * number.toDouble()
    )
}