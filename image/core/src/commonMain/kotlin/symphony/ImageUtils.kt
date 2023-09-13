package symphony

import kotlin.math.min

fun fit(src: Position, dst: Position): Position {
    val hRatio = dst.x.toDouble() / src.x
    val vRatio = dst.y.toDouble() / src.y
    val ratio = min(hRatio, vRatio)
    return Position(src.x * ratio, src.y * ratio)
}