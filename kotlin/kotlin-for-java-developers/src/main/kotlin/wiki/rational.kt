package wiki

import java.math.BigInteger

class Rational(private val numerator: BigInteger, private val denominator: BigInteger): Comparable<Rational> {
    init {
        if (denominator == 0.toBigInteger()) {
            throw IllegalArgumentException("Denominator cannot be 0")
        }
    }

    operator fun plus(other: Rational): Rational {
        val numeratorResult = (this.numerator * other.denominator) + (other.numerator * this.denominator)
        val denominatorResult = this.denominator * other.denominator

        return Rational(numeratorResult, denominatorResult).normalize()
    }

    operator fun minus(other: Rational): Rational {
        val numeratorResult = (this.numerator * other.denominator) - (other.numerator * this.denominator)
        val denominatorResult = this.denominator * other.denominator

        return Rational(numeratorResult, denominatorResult).normalize()
    }

    operator fun times(other: Rational): Rational {
        val numeratorResult = this.numerator * other.numerator
        val denominatorResult = this.denominator * other.denominator

        return Rational(numeratorResult, denominatorResult).normalize()
    }

    operator fun div(other: Rational): Rational {
        return this * Rational(other.denominator, other.numerator)
    }

    operator fun unaryMinus(): Rational = Rational(-this.numerator, this.denominator)

    override fun compareTo(other: Rational): Int =
        (numerator * other.denominator).compareTo(denominator * other.numerator)

    private fun normalize(): Rational {
        val gcd = this.numerator.gcd(this.denominator)

        if(denominator < 0.toBigInteger()) {
            return Rational(- numerator / gcd, - denominator / gcd)
        }

        return Rational(numerator / gcd, denominator / gcd)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Rational) {
            val thisNormalized = this.normalize()
            val otherNormalized = other.normalize()
            return thisNormalized.numerator == otherNormalized.numerator
                    && thisNormalized.denominator == otherNormalized.denominator
        }

        return false
    }

    override fun hashCode(): Int {
        return this.numerator.hashCode() * this.denominator.hashCode()
    }

    override fun toString(): String {
        val normalized = this.normalize()
        return if (normalized.denominator.intValueExact() == 1) normalized.numerator.toString()
            else "${normalized.numerator}/${normalized.denominator}"
    }
}

infix fun Int.divBy(denominator: Int) =
    Rational(this.toBigInteger(), denominator.toBigInteger())

infix fun Long.divBy(denominator: Long) =
    Rational(this.toBigInteger(), denominator.toBigInteger())

infix fun BigInteger.divBy(denominator: BigInteger) =
    Rational(this, denominator)

fun String.toRational(): Rational {
    val split: List<String> = this.split("/")

    return Rational(split[0].toBigInteger(), split[1].toBigInteger())
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}