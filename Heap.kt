package com.smilingbitstudios.datastructures

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
//Generic collection interface to implement Heap
interface Collection<T> {
    val count: Int
        get
    val isEmpty: Boolean
        get() = count == 0
    fun insert(element: T)
    fun remove(): T?
    fun remove(index: Int): T?
}

//Interface definition
interface Heap<T> : Collection<T> {
    fun peek(): T?  //Generalization method to peek min or max
}

abstract class AbstractHeap<T>() : Heap<T> {
    var elements: ArrayList<T> = ArrayList<T>()
    override val count: Int
        get() = elements.size
    override fun peek(): T? = elements.first()
    override fun insert(element: T) {
        elements.add(element) // 1
        siftUp(count - 1) // 2
    }

    private fun siftUp(index: Int) {
        var child = index
        var parent = parentIndex(child)
        while (child > 0 && compare(elements[child], elements[parent]) > 0) {
            Collections.swap(elements, child, parent)
            child = parent
            parent = parentIndex(child)
        }
    }

    override fun remove(): T? {
        if (isEmpty) return null // 1
        Collections.swap(elements, 0, count - 1) // 2
        val item = elements.removeAt(count - 1) // 3
        siftDown(0) // 4
        return item
    }

    private fun siftDown(index: Int) {
        var parent = index // 1
        while (true) { // 2
            val left = leftChildIndex(parent) //3
            val right = rightChildIndex(parent)
            var candidate = parent // 4
            if (left < count &&
                compare(elements[left], elements[candidate]) > 0
            ) {
                candidate = left //5
            }
            if (right < count &&
                compare(elements[right], elements[candidate]) > 0
            ) {
                candidate = right // 6
            }
            if (candidate == parent) {
                return // 7
            }
            Collections.swap(elements, parent, candidate) // 8
            parent = candidate
        }
    }

    override fun remove(index: Int): T? {
        if (index >= count) return null // 1
        return if (index == count - 1) {
            elements.removeAt(count - 1) // 2
        } else {
            Collections.swap(elements, index, count - 1) // 3
            val item = elements.removeAt(count - 1) // 4
            siftDown(index) // 5
            siftUp(index)
            item
        }
    }

    private fun index(element: T, i: Int): Int? {
        if (i >= count) {
            return null // 1
        }
        if (compare(element, elements[i]) > 0) {
            return null // 2
        }
        if (element == elements[i]) {
            return i // 3
        }
        val leftChildIndex = index(element, leftChildIndex(i))
        if (leftChildIndex != null) return leftChildIndex // 4
        val rightChildIndex = index(element, rightChildIndex(i))
        if (rightChildIndex != null) return rightChildIndex // 5
        return null // 6
    }

    protected fun heapify(values: ArrayList<T>) {
        elements = values
        if (!elements.isEmpty()) {
            (count / 2 downTo 0).forEach {
                siftDown(it)
            }
        }
    }
    private fun leftChildIndex(index: Int) = (2 * index) + 1    //index is 0-based
    private fun rightChildIndex(index: Int) = (2 * index) + 2
    private fun parentIndex(index: Int) = (index - 1) / 2
    abstract fun compare(a: T, b: T): Int
}

class ComparableHeapImpl<T : Comparable<T>> :
    AbstractHeap<T>() {
    companion object {
        fun <T : Comparable<T>> create(
            elements: ArrayList<T>
        ): Heap<T> {
            val heap = ComparableHeapImpl<T>()
            heap.heapify(elements)
            return heap
        }
    }
    override fun compare(a: T, b: T): Int = a.compareTo(b)
}

class ComparatorHeapImpl<T>(
    private val comparator: Comparator<T>
) : AbstractHeap<T>() {

    companion object {
        fun <T> create(
            elements: ArrayList<T>,
            comparator: Comparator<T>
        ): Heap<T> {
            val heap = ComparatorHeapImpl(comparator)
            heap.heapify(elements)
            return heap
        }
    }
    override fun compare(a: T, b: T): Int =
        comparator.compare(a, b)
}

fun getNthSmallestElement(n: Element): Element? {
    var current = 1 // 1
    while (!isEmpty) { // 2
        val element = remove() // 3
        if (current == n) { // 4
            return element
        }
        current += 1 // 5
    }
    return null // 6
}

override fun merge(heap: AbstractHeap<Element>) {
    elements.addAll(heap.elements)
    buildHeap()
}
private fun buildHeap() {
    if (!elements.isEmpty()) {
        (count / 2 downTo 0).forEach {
            siftDown(it)
        }
    }
}
override fun isMinHeap(): Boolean {
    if (isEmpty) return true // 1
    (count / 2 - 1 downTo 0).forEach {
// 2
        val left = leftChildIndex(it) // 3
        val right = rightChildIndex(it)
        if (left < count &&
            compare(elements[left], elements[it]) < 0) { // 4
            return false
        }
        if (right < count
            && compare(elements[right], elements[it]) < 0) { // 5
            return false
        }
    }
    return true // 6
}