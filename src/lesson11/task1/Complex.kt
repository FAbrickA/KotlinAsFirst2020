@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import kotlin.math.pow

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    try {
        if (!s.endsWith("i")) throw IllegalArgumentException()
        val signIndex = s.indexOfFirst { it == '+' || it == '-' }
        if (signIndex == -1) throw IllegalArgumentException()
        return Complex(
            s.substring(0, signIndex).toDouble(),
            s.substring(signIndex, s.length - 1).toDouble()
        )
    } catch (e: Exception) {
        throw IllegalArgumentException("Incorrect complex number")
    }

}

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(TODO(), TODO())

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = this + -other

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(
        re * other.re - im * other.im,
        re * other.im + im * other.re
    )

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex {
        val divider = other.re.pow(2) + other.im.pow(2)
        return Complex(
            (re * other.re + im * other.im) / divider,
            (im * other.re - re * other.im) / divider
        )
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && (re == other.re && im == other.im)

    /**
     * Преобразование в строку
     */
    override fun toString(): String = "$re+${im}i"

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}
