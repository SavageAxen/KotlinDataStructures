package com.smilingbitstudios.datastructures

import java.util.Collections.max

class AVLNode {
    var height = 0
    val leftHeight: Int
        get() = leftChild?.height ?: -1
    val rightHeight: Int
        get() = rightChild?.height ?: -1
    val balanceFactor: Int
        get() = leftHeight - rightHeight

    private fun leftRotate(node: AVLNode<T>): AVLNode<T> {
// 1
        val pivot = node.rightChild!!
// 2
        node.rightChild = pivot.leftChild
// 3
        pivot.leftChild = node
// 4
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        // 5
        return pivot
    }
    private fun rightRotate(node: AVLNode<T>): AVLNode<T> {
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }
    private fun rightLeftRotate(node: AVLNode<T>): AVLNode<T> {
        val rightChild = node.rightChild ?: return node
        node.rightChild = rightRotate(rightChild)
        return leftRotate(node)
    }
    private fun balanced(node: AVLNode<T>): AVLNode<T> {
        return when (node.balanceFactor) {
            2 -> {
            }
            -2 -> {
            }
            else -> node
        }
    }
    private fun balanced(node: AVLNode<T>): AVLNode<T> {
        return when (node.balanceFactor) {
            2 -> {
                if (node.leftChild?.balanceFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rightRotate(node)
                }
            }
            -2 -> {
                if (node.rightChild?.balanceFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotate(node)
                }
            }
            else -> node
        }
    }
    private fun insert(node: AVLNode<T>?, value: T): AVLNode<T>? {
        node ?: return AVLNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode?.height = max(balancedNode?.leftHeight ?: 0,
            balancedNode?.rightHeight ?: 0) + 1
        return balancedNode
    }
}