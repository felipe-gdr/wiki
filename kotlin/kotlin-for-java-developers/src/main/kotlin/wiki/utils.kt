package wiki

infix fun Any.eq(compare: Any) {
   check(this == compare) { "values are different" }
}
