package wiki

val set = hashSetOf(1, 2, 3);
var list = arrayListOf(1, 2, 3);

// "to" itself is an extension function that generates a kotlin.Pair
val map = hashMapOf(1 to "one", 2 to "two", 3 to "three")

fun main() {
    // Kotlin uses native Java collection classes
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    // some extension functions that "extend" java classes' api
    println(list.joinToString())
    println(list.joinToString(separator = " - ", postfix = "]", prefix = "["))
    println(list.getOrNull(100))

    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }

    // until is an extension function for Int that is marked as "infix"
    1 until 10
}
