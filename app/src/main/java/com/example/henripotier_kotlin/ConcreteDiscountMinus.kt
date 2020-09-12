package com.example.henripotier_kotlin

class ConcreteDiscountMinus constructor(val valueDiscount: Double) : Discount {

    override fun getDiscount(totalPrice: Double) : Double {
        return valueDiscount;
    }
}