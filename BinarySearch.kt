package com.smilingbitstudios.datastructures

// recursive extension function to search on any ArrayList
fun <T : Comparable<T>> ArrayList<T>.binarySearch(
    value: T, range: IntRange = indices ): Int? {
    if (range.first > range.last) {
        return null // return if range not valid
    }
    val size = range.last - range.first + 1 // range size
    val middle = range.first + size / 2 // middle point
    return when {
        this[middle] == value -> middle // if value found in the middle
        // recursive calls
        this[middle] > value -> binarySearch(
            value,
            range.first until middle
        ) // seek ind lower middle
        else -> binarySearch(value, (middle + 1)..range.last)   // seek in higher middle
    }
}
    //unoptimized function to find range of repeated element
//    fun <T : Comparable<T>> ArrayList<T>.findIndices(value: T ): IntRange? {
//        val startIndex = indexOf(value)
//        val endIndex = lastIndexOf(value)
//        if (startIndex == -1 || endIndex == -1) {
//            return null
//        }
//        return startIndex..endIndex
//    }
//}
//optimized function to find range of repeated element
fun <T : Comparable<T>> ArrayList<T>.findIndices( value: T ): IntRange? {
    val startIndex = startIndex(value, 0..size) ?: return null
    val endIndex = endIndex(value, 0..size) ?: return null
    return startIndex until endIndex
    }
// Helper function to find first index of a repeating element
private fun <T : Comparable<T>> ArrayList<T>.startIndex(
    value: T, range: IntRange): Int? {
    val middleIndex = range.start + (range.last - range.start + 1) / 2
    if (middleIndex == 0 || middleIndex == size - 1) {
        return if (this[middleIndex] == value) {
            middleIndex
        } else {
            null
        }
    }
    return if (this[middleIndex] == value) {
        if (this[middleIndex - 1] != value) {
            middleIndex
        } else {
            startIndex(value, range.start until middleIndex)
        }
    } else if (value < this[middleIndex]) {
        startIndex(value, range.start until middleIndex)
    } else {
        startIndex(value, (middleIndex + 1)..range.last)
    }
}
private fun <T : Comparable<T>> ArrayList<T>.endIndex(
    value: T,range: IntRange): Int? {
    val middleIndex = range.start + (range.last - range.start + 1) / 2
    if (middleIndex == 0 || middleIndex == size - 1) {
        return if (this[middleIndex] == value) {
            middleIndex + 1
        } else {
            null
        }
    }
    return if (this[middleIndex] == value) {
        if (this[middleIndex + 1] != value) {
            middleIndex + 1
        } else {
            endIndex(value, (middleIndex + 1)..range.last)
        }
    } else if (value < this[middleIndex]) {
        endIndex(value, range.start until middleIndex)
    } else {
        endIndex(value, (middleIndex + 1)..range.last)
    }
}
