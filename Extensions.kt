package com.smilingbitstudios.datastructures


fun Trie<Char>.insert(string: String) {
    insert(string.toList())
}
fun Trie<Char>.contains(string: String): Boolean {
    return contains(string.toList())
}
fun Trie<Char>.remove(string: String) {
    remove(string.toList())
}
//map input string into list of char, then list in result to collections
fun Trie<Char>.collections(prefix: String): List<String> {
    return collections(prefix.toList()).map
    { it.joinToString(separator = "") }
}

