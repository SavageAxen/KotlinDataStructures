package com.smilingbitstudios.datastructures

interface Queue<T> {

    fun enqueue(element: T): Boolean

    fun dequeue(): T?

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?
}

    abstract class QueueImp<T>: Queue<T>{
        val value: T? =null

        override fun toString(): String {
            return value.toString()
    }

}