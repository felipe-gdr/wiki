package wiki

// Create an extension function for the class String
fun String.lastChar() = get(length - 1)

// Extension function is shadowed by member function (ie: member function will always be called instead or extension function)
fun String.get(i: Int) = "*"

// Extension function overloads member function
fun String.get() = "*"

fun List<Int>.sum(): Int {
    var result = 0

    for (i in this) {
        result += i
    }

    return result
}

fun main() {
    println("hey".lastChar())

    val list = listOf(1,2,3)

    println(list.sum())
}