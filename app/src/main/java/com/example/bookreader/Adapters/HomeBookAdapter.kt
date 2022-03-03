package com.example.bookreader.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookreader.Book
import com.example.bookreader.OnClickListnerHome
import com.example.bookreader.R
import kotlinx.android.synthetic.main.book_item.view.*

class HomeBookAdapter(val fragment: Fragment, val books: MutableList<Book>, val onClickListnerHome: OnClickListnerHome) :
    RecyclerView.Adapter<HomeBookAdapter.ViewHolderBook>() {
    class ViewHolderBook(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: Book) {
            Glide.with(itemView).load(book.bookCover).into(itemView.bookCover)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBook {
        return ViewHolderBook(
            LayoutInflater.from(fragment.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderBook, position: Int) {
        holder.bind(books[position])
        holder.itemView.setOnClickListener{
            onClickListnerHome.onClickListnerTwo(books[position])
        }
    }

    override fun getItemCount() = books.size

}

