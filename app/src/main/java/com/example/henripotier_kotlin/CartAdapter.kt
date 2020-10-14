package com.example.henripotier_kotlin

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class CartAdapter(private val context: Context, private val dataSource: MutableList<Book>, private val onRemove: () -> Unit) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun updateReceiptsList(newlist: MutableList<Book>) {
        dataSource.clear()
        dataSource.addAll(newlist)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.row_cart, parent, false)
        val subtitleTextView = rowView.findViewById(R.id.price_textView) as? TextView
        val titleTextView = rowView.findViewById(R.id.titleBook_textView) as? TextView
        val bookCoverImageView = rowView.findViewById(R.id.imageView) as? ImageView
        val book = getItem(position) as Book
        titleTextView?.text = book.title
        if (subtitleTextView != null) {
            subtitleTextView.text = book.price.toString()
        }
        Picasso.get().load(book.cover).into(bookCoverImageView);

        //SETONCLICK REMOVE Button
        (rowView.findViewById(R.id.remove_button) as? Button)?.setOnClickListener{
            Cart.removeBook(book)
            majCart()
            updateReceiptsList(Cart.getCart().toMutableList())
            onRemove()
        }

        return rowView
    }

    private fun majCart() {
        Cart.sumCart()

    }


}
