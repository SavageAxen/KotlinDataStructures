package com.smilingbitstudios.datastructures

import android.sax.Element

interface Stack<Element> {
    fun push(element: Any)
    fun pop(): Element?
    fun peek(): Element?
    val count: Int
    val isEmpty: Boolean
        get() = count == 0
}

class StackImpl<T: Any>(): Stack<Element> {
    private val storage = arrayListOf<Element>()

    override fun toString()= buildString {
        appendLine("--top--")
        storage.asReversed().forEach{
            appendLine("$it")
        }
        appendLine("-----")
    }

    override fun push(element: Any) {
        storage.add(element as Element)
    }
    override fun pop(): Element? {
        if (storage.size == 0) {
            return null
        }
        return storage.removeAt(count - 1)
    }
    override fun peek(): Element? {
        return storage.lastOrNull()
    }

    override val count: Int
        get() = storage.size

    companion object {
        fun <Element : Any> create(items: Iterable<Element>):
                StackImpl<Element> {
            val stack = StackImpl<Element>()
            for (item in items) {
                stack.push(item)
            }
            return stack
        }

    }
}
fun <Element : Any> StackOf(vararg elements: Element): StackImpl<Element>
{
    return StackImpl.create(elements.asList())
}