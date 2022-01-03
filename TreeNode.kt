package com.smilingbitstudios.datastructures

import Queue
import java.util.*
import java.util.function.consumer
typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
    private val children: MutableList<TreeNode<T>>=MutableListOf()
    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)     // apply action on node
        children.forEach {
            it.forEachDepthFirst(visit)     //recursive call
        }
    }
    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = Queue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }
        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }
    fun search(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null

        forEachLevelOrder {
            if (it.value == value) {
                result = it
            }
        }

        return result
    }
}

