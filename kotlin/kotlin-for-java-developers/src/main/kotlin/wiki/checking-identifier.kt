package wiki

/**
 * Kotlin playground from week 2: Control structures
 *
 * link: https://www.coursera.org/learn/kotlin-for-java-developers/ungradedWidget/Yqyi3/kotlin-playground-checking-identifier
 */
fun isValidIdentifier(string: String): Boolean {
    if(string == "") return false

    for((index, ch) in string.withIndex()) {

        if(ch !in 'a'..'z' && ch !in 'A'..'Z' && ch !in '0'..'9' && ch != '_') {
            return false
        }

        if(index == 0 && (ch in '0'..'9')) {
            return false
        }
    }

    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}