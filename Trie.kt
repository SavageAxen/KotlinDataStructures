package com.smilingbitstudios.datastructures

class Trie {
    private val root = TrieNode<Key>(key = null, parent = null)

    private val storedLists: MutableSet<List<Key>> = mutableSetOf()
    val lists: List<List<Key>>
        get() = storedLists.toList()
    val count: Int
        get() = storedLists.count()
    val isEmpty: Boolean
        get() = storedLists.isEmpty()

    fun insert(list: List<Key>) {
        var current = root // root node
        list.forEach { element ->
            if (current.children[element] == null) {
                current.children[element] = TrieNode(element, current)
            }
            current = current.children[element]!!
        }
        current.isTerminating = true //mark node as a end node
        storedLists.add(list)
    }
    fun contains(list: List<Key>): Boolean {
        var current = root  // root node
        list.forEach { element ->   // search for node
            val child = current.children[element] ?: return false
            current = child
        }
        return current.isTerminating    //return last node
    }
    // remove tries by getting a list of values
    fun remove(collection: CollectionType) {
        var current = root  //root node
        collection.forEach {    //basically contains...
            val child = current.children[it] ?: return
            current = child
        }
        if (!current.isTerminating) return // if not last node return
        current.isTerminating = false // set current as not last node to remove
        storedLists.remove(list)
        val parent = current.parent
        while (current.children.isEmpty() && !current.isTerminating) {
            parent?.let {
                it.children[current.key] = null
                current = it
            }
        }
    }
    fun collections(prefix: List<Key>): List<List<Key>> {
        var current = root
        prefix.forEach { element ->
            val child = current.children[element] ?: return emptyList()
            current = child
        }
        return collections(prefix, current)
    }

    private fun collections(prefix: List<Key>, node: TrieNode<Key>?): List<List<Key>> {
        val results = mutableListOf<List<Key>>()
        if (node?.isTerminating == true) {
            results.add(prefix) // if last node, add to results
        }
        node?.children?.forEach { (key, node) -> // search every children to add more results
            results.addAll(collections(prefix + key, node))
        }
        return results
    }
}