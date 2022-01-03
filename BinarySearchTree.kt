package com.smilingbitstudios.datastructures

class BinarySearchTree<T: Comparable<T>>() {
    var root: BinaryNode<T>? =null
    override fun toString() =root?.toString() ?:"empty tree"

    fun insert(value: T) {
        root = insert(root, value)
    }
    private fun insert( node: BinaryNode<T>?,
        value: T ): BinaryNode<T> {
    // If node is null, this is insertion point, return current node
        node ?: return BinaryNode(value)
    // Select branch to traverse
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        // Return position node to insert
        return node
    }
    fun contains(value: T): Boolean {
        root ?: return false
        var found = false
        root?.traverseInOrder {
            if (value == it) {
                found = true
            }
        }
        return found
    }

    fun remove(value: T) {
        root = remove(root, value)
    }
    private fun remove(
        node: BinaryNode<T>?,
        value: T
    ): BinaryNode<T>? {
        node ?: return null
        when {
            value == node.value -> {
// more to come
            }
            value < node.value -> node.leftChild =
                remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        return node
    }

}