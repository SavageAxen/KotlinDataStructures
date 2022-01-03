package com.smilingbitstudios.datastructures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    val tree = makeBeverageTree()
//    tree.forEachDepthFirst { println(this.value) }

    //"creating and linking nodes"
    fun LinkNode() {
        val node1 = Node(value = 1)
        val node2 = Node(value = 2)
        val node3 = Node(value = 3)
        node1.next = node2
        node2.next = node3
        println(node1)
    }

    //"push"
    fun pushNode() {
        val list = LinkedList<Int>()
        list.push(3)
        list.push(2)
        list.push(1)
        println(list)
    }

    // "inserting at a particular index"
    fun insertNode() {
        val list = LinkedList<Int>()
        list.push(3)
        list.push(2)
        list.push(1)
        println("Before inserting: $list")
        var middleNode = list.nodeAt(1)!!
        for (i in 1..3) {
            middleNode = list.insert(-1 * i, middleNode)
        }
        println("After inserting: $list")
    }

    //"pop"
    fun popNode() {
        val list = LinkedList<Int>()
        list.push(3)
        list.push(2)
        list.push(1)
        println("Before popping list: $list")
        val poppedValue = list.pop()
        println("After popping list: $list")
        println("Popped value: $poppedValue")
    }

    //"removing the last node"
    fun removeExample() {
        val list = LinkedList<Int>()
        list.push(3)
        list.push(2)
        list.push(1)
        println("Before removing last node: $list")
        val removedValue = list.removeLast()
        println("After removing last node: $list")
        println("Removed value: $removedValue")
    }

    //"removing a node after a particular node"
    fun removeAtExample() {
        val list = LinkedList<Int>()
        list.push(3)
        list.push(2)
        list.push(1)
        println("Before removing at particular index: $list")
        val index = 1
        val node = list.nodeAt(index - 1)!!
        val removedValue = list.removeAfter(node)
        println("After removing at index $index: $list")
        println("Removed value: $removedValue")
    }

    //"printing doubles"
    fun iterableExample() {
        val list = LinkedListIterable<Int>()
        list.push(3)
        list.push(2)
        list.push(1)
        println(list)
        for (item in list) {
            println("Double: ${item * 2}")
        }
    }

    //"removing elements mutable"
    fun mutableExample() {
        val list: MutableCollection<Int> = LinkedListCollection()
        list.add(3)
        list.add(2)
        list.add(1)
        println(list)
        list.remove(1)
        println(list)
    }

    //"retaining elements mutable"
    fun retainExample() {
        val list: MutableCollection<Int> = LinkedListCollection()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)
        println(list)
        list.retainAll(listOf(3, 4, 5))
        println(list)
    }

    //"remove all elements mutable"
    fun removeMutableExample() {
        val list: MutableCollection<Int> = LinkedListCollection()
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(4)
        list.add(5)
        println(list)
        list.removeAll(listOf(3, 4, 5))
        println(list)
    }

    //"using a stack"
    fun stackExample() {
        val stack = StackImpl<Int>().apply {
            push(1)
            push(2)
            push(3)
            push(4)
        }
        print(stack)
        val poppedElement = stack.pop()
        if (poppedElement != null) {
            println("Popped: $poppedElement")
        }
        print(stack)
    }

    //"initializing a stack from a list"
    fun initStackExample() {
        val list = listOf("A", "B", "C", "D")
        val stack = StackImpl.create(list)
        print(stack)
        println("Popped: ${stack.pop()}")
    }

    //"initializing a stack from an array literal"
    fun initStackOfexample() {
        val stack = StackImpl.StackOf(1.0, 2.0, 3.0, 4.0)
        print(stack)
        println("Popped: ${stack.pop()}")
    }

    private fun makeBeverageTree(): TreeNode<String> {
        val tree = TreeNode("Beverages")
        val hot = TreeNode("hot")
        val cold = TreeNode("cold")
        val tea = TreeNode("tea")
        val coffee = TreeNode("coffee")
        val chocolate = TreeNode("cocoa")
        val blackTea = TreeNode("black")
        val greenTea = TreeNode("green")
        val chaiTea = TreeNode("chai")
        val soda = TreeNode("soda")
        val milk = TreeNode("milk")
        val gingerAle = TreeNode("ginger ale")
        val bitterLemon = TreeNode("bitter lemon")
        tree.add(hot)
        tree.add(cold)
        hot.add(tea)
        hot.add(coffee)
        hot.add(chocolate)
        cold.add(soda)
        cold.add(milk)
        tea.add(blackTea)
        tea.add(greenTea)
        tea.add(chaiTea)
        soda.add(gingerAle)
        soda.add(bitterLemon)
        return tree
    }

    //"building a BST"
    private fun buildBST() {
        val bst = BinarySearchTree<Int>()
        (0..4).forEach {
            bst.insert(it)
        }
        println(bst)
    }

    val exampleTree = BinarySearchTree<Int>().apply {
        insert(3)
        insert(1)
        insert(4)
        insert(0)
        insert(2)
        insert(5)
    }

    //"building a BST"
    fun printBST() {
        println(exampleTree)
    }

    //"finding a node"
    fun findNode() {
        if (exampleTree.contains(5)) {
            println("Found 5!")
        } else {
            println("Couldn't find 5")
        }
    }

    //removing a node"
    fun removeNode() {
        println("Tree before removal:")
        println(exampleTree)
        exampleTree.remove(3)
        println("Tree after removing root:")
        println(exampleTree)
    }

    //"repeated insertions in sequence"
    fun example() {
        val tree = AVLNode<Int>()
        (0..14).forEach {
            tree.insert(it)
        }
        print(tree)
    }

    //"insert and contains tries without extension functions
    fun containsTries() {
        val trie = Trie<Char>()
        trie.insert("cute".toList())
        if (trie.contains("cute".toList())) {
            println("cute is in the trie")
        }
    }

    //insert and contains tries with extension functions
    fun containsTriesEx() {
        val trie = Trie<Char>()
        trie.insert("cute")
        if (trie.contains("cute")) {
            println("cute is in the trie")
        }
    }

    //"remove trie example"
    fun removeTrie() {
        val trie = Trie<Char>()
        trie.insert("cut")
        trie.insert("cute")
        println("\n*** Before removing ***")
        assert(trie.contains("cut"))
        println("\"cut\" is in the trie")
        assert(trie.contains("cute"))
        println("\"cute\" is in the trie")
        println("\n*** After removing cut ***")
        trie.remove("cut")
        assert(!trie.contains("cut"))
        assert(trie.contains("cute"))
        println("\"cute\" is still in the trie")
    }

    //"prefix matching in tries"
    fun searchTries() {
        val trie = Trie<Char>().apply {
            insert("car")
            insert("card")
            insert("care")
            insert("cared")
            insert("cars")
            insert("carbs")
            insert("carapace")
            insert("cargo")
        }
        println("\nCollections starting with \"car\"")
        val prefixedWithCar = trie.collections("car")
        println(prefixedWithCar)
        println("\nCollections starting with \"care\"")
        val prefixedWithCare = trie.collections("care")
        println(prefixedWithCare)
    }

    //"binary search"
    fun binarySearch() {
        val array = arrayListOf(
            1, 5, 15, 17, 19, 22, 24, 31, 105,
            150
        )
        val search31 = array.indexOf(31)
        val binarySearch31 = array.binarySearch(31)
        println("indexOf(): $search31")
        println("binarySearch(): $binarySearch31")
    }

    //"binary search for a range"
    fun bsIndexRange() {
        val array = arrayListOf(1, 2, 3, 3, 3, 4, 5, 5)
        val indices = array.findIndices(3)
        println(indices)
    }

    fun heapComparatot() {
        val array = arrayListOf(1, 12, 3, 4, 1, 6, 8, 7) // 1
        val inverseComparator = object : Comparator<Int> { // 2
            override fun compare(o1: Int, o2: Int): Int = o2.compareTo(o1)
        }
        val minHeap = ComparatorHeapImpl.create(array, inverseComparator) // 3
        while (!minHeap.isEmpty)
        { // 4
            println(minHeap.remove())
        }
    }
    //"max priority queue"
    fun maxpQueue() {
    // 1
        val priorityQueue = ComparablePriorityQueueImpl<Int>()
    // 2
        arrayListOf(1, 12, 3, 4, 1, 6, 8, 7).forEach {
            priorityQueue.enqueue(it)
        }
    // 3
        while (!priorityQueue.isEmpty) {
            println(priorityQueue.dequeue())
        }
    }
    //"min priority queue"
    fun minpQueue() {
// 1
        val stringLengthComparator = object : Comparator<String> {
            override fun compare(o1: String?, o2: String?): Int {
                val length1 = o1?.length ?: -1
                val length2 = o2?.length ?: -1
                return length1 - length2
            }
        }
// 2
        val priorityQueue =
            ComparatorPriorityQueueImpl(stringLengthComparator)
// 3
        arrayListOf("one", "two", "three", "forty", "five", "six",
            "seven", "eight", "nine").forEach {
            priorityQueue.enqueue(it)
        }
// 4
        while (!priorityQueue.isEmpty) {
            println(priorityQueue.dequeue())
        }
    }
        }
}