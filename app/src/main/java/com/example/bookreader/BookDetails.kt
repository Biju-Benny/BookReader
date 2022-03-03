package com.example.bookreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookName: String = intent.getStringExtra("BookName").toString()
        val author: String = intent.getStringExtra("Author").toString()
        val bookCover: String = intent.getStringExtra("BookCover").toString()
        val content: String =intent.getStringExtra("Content").toString()

        Glide.with(this).load(bookCover).into(bookCoverBd)
        bookNameBd.text = bookName
        authorBd.text = author
        contentBd.text = content
    }
}