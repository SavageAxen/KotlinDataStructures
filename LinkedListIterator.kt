package com.smilingbitstudios.datastructures

class LinkedListIterator<T>(private val list: LinkedListIterable<T>):
    Iterator<T>, MutableIterator<T>{
    private var index = 0
    private var lastNode: Node<T>? = null

    override fun next(): T {
        //if there are no members to return, throw error
        if (index >= list.size) throw IndexOutOfBoundsException()
        // in first iteration, search and set last node
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else {
            lastNode?.next
        }
        // update index
        index++
        return lastNode!!.value
    }

    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun remove() {
        if(index==1){
            list.pop()
        } else {
            val prevNode = list.nodeAt(index - 2)?: return
            list.removeAfter(prevNode)
            lastNode=prevNode
        }
    }
}