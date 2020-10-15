package com.example.henripotier_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking


class CartRecyclerViewAdapter(var books: MutableList<Book>, private val onRemove: () -> Unit) : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRecyclerViewAdapter.ViewHolder {
        val rowBooks = LayoutInflater.from(parent.context).inflate(R.layout.row_cart, parent, false)
        return ViewHolder(rowBooks)
    }

    override fun onBindViewHolder(holder: CartRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.titleView?.text = books[position].title
        holder.subtitleTextView?.text = books[position].price.toString()
        Picasso.get().load(books[position].cover).into(holder?.bookCoverImageView);
        holder.suppButton?.setOnClickListener{
            println(books[position])
            println("Ancienne list : " + Cart.getCart().toString())
            println(Cart.getCart().size)
            runBlocking { Cart.removeBook(books[position]) }
            val newBooks = Cart.getCart().toList()
            val diffResult = updateList(newBooks)
            books.clear()
            books.addAll(newBooks)
            onRemove()
            diffResult.dispatchUpdatesTo(this)
            println(Cart.getCart().size)
        }
    }


    override fun getItemCount(): Int {
        return books.size
    }


    fun updateList(newList: List<Book>) : DiffUtil.DiffResult{
        return DiffUtil.calculateDiff( MyDiffCallBack(books, newList))
    }

    class ViewHolder( val rowBook: View) : RecyclerView.ViewHolder(rowBook){
        var titleView: TextView? = null
        var subtitleTextView : TextView? = null
        var bookCoverImageView : ImageView? = null
        var suppButton : Button? = null
        init{
            titleView = rowBook.findViewById(R.id.titleBook_textView)
            subtitleTextView = rowBook.findViewById(R.id.price_textView)
            bookCoverImageView = rowBook.findViewById(R.id.imageView)
            suppButton = rowBook.findViewById(R.id.remove_button)
        }

    }

}