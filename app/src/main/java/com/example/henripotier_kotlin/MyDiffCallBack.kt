package com.example.henripotier_kotlin

import androidx.recyclerview.widget.DiffUtil

class MyDiffCallBack(oldList : MutableList<Book>, newList: MutableList<Book>) : DiffUtil.Callback() {
    val oldList : MutableList<Book> = oldList
    val newList : MutableList<Book> = newList

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isbn == newList[newItemPosition].isbn
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equals(newList[newItemPosition])
    }

}