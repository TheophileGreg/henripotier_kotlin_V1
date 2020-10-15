package com.example.henripotier_kotlin

import androidx.recyclerview.widget.DiffUtil

class MyDiffCallBack(oldList : MutableList<Book>, newList: List<Book>) : DiffUtil.Callback() {
    val oldList : MutableList<Book> = oldList
    val newList : List<Book> = newList

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size

    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (newItemPosition < newList.size) return oldList[oldItemPosition].isbn == newList[newItemPosition].isbn
        else return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (newItemPosition < newList.size) return oldList[oldItemPosition] == newList[newItemPosition]
        else return false
    }

}