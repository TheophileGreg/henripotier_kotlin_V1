package com.example.henripotier_kotlin

class ConcreteDiscountPercentage constructor(val percentageValue: Double)  : Discount{
    override fun getDiscount(totalPrice: Double) : Double {
        return (totalPrice / 100) * percentageValue
    }

}