package com.example.henripotier_kotlin

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONArray
import java.io.Serializable


class Book(var isbn: String? = null,
           var title: String? = null,
           var price: Int? = 0,
           var cover: String? = null,
           var synopsis: ArrayList<String> = ArrayList()) : Parcelable {
    private constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.createStringArrayList() as ArrayList<String>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isbn)
        parcel.writeString(title)
        parcel.writeValue(price)
        parcel.writeString(cover)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}


