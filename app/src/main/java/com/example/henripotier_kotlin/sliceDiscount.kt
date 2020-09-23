package com.example.henripotier_kotlin

data class sliceDiscount(val sliceValue: Double, val valueMinus : Double){
    fun isValid(): Boolean{
        return sliceValue >= valueMinus
    }
}