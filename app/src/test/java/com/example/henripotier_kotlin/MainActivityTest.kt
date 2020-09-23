package com.example.henripotier_kotlin

import org.junit.Test

import org.junit.Assert.*

class MainActivityTest {

    @Test
    fun getBestDiscountTest() {
        val sliceOne = sliceDiscount(60.0, 5.0);
        val discountsOne = Discounts(5.0, 10.0,sliceOne )
        val sliceTwo = sliceDiscount(80.0, 6.0)
        val discountsTwo = Discounts(9.0, 15.0, sliceTwo)
        val sliceThree = sliceDiscount(75.0, 20.0)
        val discountsThree = Discounts(3.0, 11.0, sliceThree)
        val sliceFour = sliceDiscount(0.0, 10.0)
        val discountsFour = Discounts(11.0, 17.0, sliceFour)

        assertEquals(10.0, discountsOne.getBestDiscount(100.0), 1.0);
        assertEquals(20.0, discountsOne.getBestDiscount(200.0), 1.0);
        assertEquals(9.0, discountsTwo.getBestDiscount(15.0), 1.0);
        assertEquals(20.0, discountsThree.getBestDiscount(90.0), 1.0);
    }
}