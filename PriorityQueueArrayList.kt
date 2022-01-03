package com.smilingbitstudios.datastructures

import java.util.*
import kotlin.Comparator

abstract class AbstractPriorityQueueArrayList<T> : Queue<T> {

    protected val elements = ArrayList<T>()

    abstract fun sort()

    override val count: Int
        get() = elements.size

    override fun enqueue(element: T): Boolean {
        elements.add(element)
        sort()
        return true
    }

    override fun dequeue() =
        if (isEmpty) null else elements.removeAt(0)

    override fun peek() = elements.firstOrNull()

    override fun toString() = elements.toString()
}

class ComparablePriorityQueueArrayList<T : Comparable<T>> : AbstractPriorityQueueArrayList<T>() {
    override fun sort() {
        Collections.sort(elements)
    }
}

class ComparatorPriorityQueueArrayList<T>(
    private val comparator: Comparator<T>
) : AbstractPriorityQueueArrayList<T>() {
    override fun sort() {
        Collections.sort(elements, comparator)
    }
}

class CustomPriorityQueueArrayList<T : Comparable<T>> : AbstractPriorityQueueArrayList<T>() {
    override fun sort() {
        var index = count - 2
        while (index >= 0 &&
            elements[index + 1].compareTo(elements[index]) > 0) {
            swap(index, index + 1)
            index--;
        }
    }

    private fun swap(i: Int, j: Int) {
        val tmp = elements[i]
        elements[i] = elements[j]
        elements[j] = tmp
    }
}