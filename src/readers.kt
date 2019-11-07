internal fun readln() = readLine()!!
internal fun readlnByte() = readln().toByte()
internal fun readlnShort() = readln().toShort()
internal fun readlnInt() = readln().toInt()
internal fun readlnLong() = readln().toLong()
internal fun readlnFloat() = readln().toFloat()
internal fun readlnDouble() = readln().toDouble()
internal fun readlnBigInt(radix: Int = 10) = readln().toBigInteger(radix)
internal fun readlnBigDecimal() = readln().toBigDecimal()

internal fun lineSequence(limit: Int = Int.MAX_VALUE) = generateSequence { readLine() }.constrainOnce().take(limit)
internal fun readlnStrings() = readln().split(' ')
internal fun readlnBytes() = readlnStrings().map { it.toByte() }
internal fun readlnShorts() = readlnStrings().map { it.toShort() }
internal fun readlnInts() = readlnStrings().map { it.toInt() }
internal fun readlnLongs() = readlnStrings().map { it.toLong() }
internal fun readlnFloats() = readlnStrings().map { it.toFloat() }
internal fun readlnDoubles() = readlnStrings().map { it.toDouble() }

internal fun readByteArray() = readlnStrings().run { ByteArray(size) { get(it).toByte() } }
internal fun readShortArray() = readlnStrings().run { ShortArray(size) { get(it).toShort() } }
internal fun readIntArray() = readlnStrings().run { IntArray(size) { get(it).toInt() } }
internal fun readLongArray() = readlnStrings().run { LongArray(size) { get(it).toLong() } }
internal fun readFloatArray() = readlnStrings().run { FloatArray(size) { get(it).toFloat() } }
internal fun readDoubleArray() = readlnStrings().run { DoubleArray(size) { get(it).toDouble() } }

internal fun readlnByteArray(n: Int) = ByteArray(n) { readlnByte() }
internal fun readlnShortArray(n: Int) = ShortArray(n) { readlnShort() }
internal fun readlnIntArray(n: Int) = IntArray(n) { readlnInt() }
internal fun readlnLongArray(n: Int) = LongArray(n) { readlnLong() }
internal fun readlnFloatArray(n: Int) = FloatArray(n) { readlnFloat() }
internal fun readlnDoubleArray(n: Int) = DoubleArray(n) { readlnDouble() }

internal fun readByteArray2d(rows: Int, cols: Int) = Array(rows) { readByteArray().also { require(it.size == cols) } }
internal fun readShortArray2d(rows: Int, cols: Int) = Array(rows) { readShortArray().also { require(it.size == cols) } }
internal fun readLongArray2d(rows: Int, cols: Int) = Array(rows) { readLongArray().also { require(it.size == cols) } }
internal fun readIntArray2d(rows: Int, cols: Int) = Array(rows) { readIntArray().also { require(it.size == cols) } }
internal fun readFloatArray2d(rows: Int, cols: Int) = Array(rows) { readFloatArray().also { require(it.size == cols) } }
internal fun readDoubleArray2d(rows: Int, cols: Int) = Array(rows) { readDoubleArray().also { require(it.size == cols) } }

internal fun isWhiteSpace(c: Char) = c in " \r\n\t"

// JVM-only targeting code follows next

// readString() via sequence is still slightly faster than Scanner
internal fun readString() = generateSequence { System.`in`.read().toChar() }
    .dropWhile { isWhiteSpace(it) }.takeWhile { !isWhiteSpace(it) }.joinToString("")
internal fun readByte() = readString().toByte()
internal fun readShort() = readString().toShort()
internal fun readInt() = readString().toInt()
internal fun readLong() = readString().toLong()
internal fun readFloat() = readString().toFloat()
internal fun readDouble() = readString().toDouble()
internal fun readBigInt(radix: Int = 10) = readString().toBigInteger(radix)
internal fun readBigDecimal() = readString().toBigDecimal()

internal fun readBytes(n: Int) = generateSequence { readByte() }.take(n)
internal fun readShorts(n: Int) = generateSequence { readShort() }.take(n)
internal fun readInts(n: Int) = generateSequence { readInt() }.take(n)
internal fun readLongs(n: Int) = generateSequence { readLong() }.take(n)
internal fun readFloats(n: Int) = generateSequence { readFloat() }.take(n)
internal fun readDoubles(n: Int) = generateSequence { readDouble() }.take(n)