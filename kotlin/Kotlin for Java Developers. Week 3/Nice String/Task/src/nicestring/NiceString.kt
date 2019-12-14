package nicestring

val vowels = listOf('a', 'e', 'i', 'o', 'u')
val bads = listOf("bu", "ba", "be").map { it.toRegex() }

val containsBad: (String) -> Boolean = { str -> bads.any { str.contains(it)} }
val containsDouble: (String) -> Boolean = { str ->
    str.zipWithNext().any { (first, second) -> first == second}
}
val contains3Vowels: (String) -> Boolean = {str ->
    str.filter { vowels.contains(it) }.length >= 3
}

fun String.isNice(): Boolean {
    return listOf(
            contains3Vowels(this),
            !containsBad(this),
            containsDouble(this)
    ).filter { it }.size >= 2
}
