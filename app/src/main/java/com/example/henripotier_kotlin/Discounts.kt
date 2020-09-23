package com.example.henripotier_kotlin

import java.lang.Exception
import kotlin.math.max

data class Discounts(val minusDiscount: Double, val percentageDiscount: Double, val sliceDiscount: sliceDiscount){

    fun getBestDiscount(cartPrice: Double): Double {
        val a = minusDiscount
        val b = (cartPrice / 100) * percentageDiscount
        val c = (cartPrice / sliceDiscount.sliceValue) * sliceDiscount.valueMinus
        if (!sliceDiscount.isValid()){
            throw Exception("The sliceDiscount argument is False")
        }
        return max(max(a, b), c)
    }

}
