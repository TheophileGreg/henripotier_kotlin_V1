package com.example.henripotier_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context, private val dataSource: ArrayList<Book>) :
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.row, parent, false)
        val titleTextView = rowView.findViewById(R.id.header) as TextView
        val subtitleTextView = rowView.findViewById(R.id.text) as TextView
        //val totalPriceTextView =

        val book = getItem(position) as Book
        titleTextView.text = book.title
        subtitleTextView.text = book.isbn

        return rowView
    }


}
