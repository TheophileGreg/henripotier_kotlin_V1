package com.example.henripotier_kotlin

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide.with
import com.squareup.picasso.Picasso

class BookAdapter(private val context: Context, private val dataSource: MutableList<Book>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.row, parent, false)
        val titleTextView = rowView.findViewById(R.id.header) as TextView
        val subtitleTextView = rowView.findViewById(R.id.text) as TextView
        val bookCoverImageView = rowView.findViewById(R.id.bookCover_image) as ImageView
        val book = getItem(position) as Book
        titleTextView.text = book.title
        subtitleTextView.text = book.synopsis[0]
        Picasso.get().load(book.cover).into(bookCoverImageView);
        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}