import java.io.Closeable

fun <T : Closeable, R> T.useWith(block: T.() -> R): R = use { with(it, block) }