package com.example.henripotier_kotlin

class ConcreteDiscountSlice constructor(val sliceValue: Double, val valueMinus: Double ): Discount{
    override fun getDiscount(totalPrice: Double) : Double {
        return totalPrice.div(sliceValue) * valueMinus
    }
}