package year2023.day05

import java.util.TreeMap

class Mapper(val src: String, val dest: String) {
    val offsets = TreeMap<Long, Offset>()

    fun map(x: Long): Long {
        val offset = offsets.floorEntry(x)

        if (offset == null || offset.value.srcEnd < x) {
            return x
        }

        return x + offset.value.diff
    }

    fun inverted(): Mapper {
        val mapper = Mapper(dest, src)

        for (offset in offsets.values) {
            mapper.offsets[offset.destStart] = offset.inverted()
        }

        return mapper
    }
}
