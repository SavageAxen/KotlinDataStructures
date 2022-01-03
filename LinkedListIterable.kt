package com.smilingbitstudios.datastructures


class LinkedListIterable<T>: Iterable<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size = 0
        private set

    override fun iterator(): Iterator<T> {
        return LinkedListIterator(this)
    }

    private fun isEmpty(): Boolean {
        return size == 0
    }

    override fun toString(): String{
        return if (isEmpty()) {
            "Empty list"
        } else {
            head.toString()
        }
    }

    fun push(value: T): LinkedListIterable<T> {
        head = Node(value = value, next = head)
        if (tail == null) { //if empty update head and tail
            tail = head
        }
        size++  //update size
        return this //return new node at tail
    }

    fun append(value: T) {
        if (isEmpty()) {// if empty update head and tail
            push(value)
            return
        }
        tail?.next = Node(value = value)    // if not empty, add node at tail
        tail = tail?.next //update tail node
        size++  //update size
    }

    fun nodeAt(index: Int): Node<T>? {
        // create reference to head
        var currentNode = head
        var currentIndex = 0
        // move to desired index
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) { append(value)// if insert at last node: append
            return tail!!
        }
        val newNode = Node(value = value, next = afterNode.next)// crete node to insert
        afterNode.next = newNode// assign new node next value
        size++
        return newNode
    }

    fun pop(): T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
        return result
    }

    fun removeLast(): T? {
        val head = head ?: return null
        if (head.next == null) return pop()  //if only one node, pop()
        size--
        var prev = head //
        var current = head
        var next = current.next
        while (next != null) { //find node before last
            prev = current
            current = next
            next = current.next
        }
        prev.next = null    //disconnect last node
        tail = prev
        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value //
        if (node.next == tail) {    //if last node, update tail
            tail = node
        }
        if (node.next != null) {    //
            size--
        }
        node.next = node.next?.next
        return result
    }
}