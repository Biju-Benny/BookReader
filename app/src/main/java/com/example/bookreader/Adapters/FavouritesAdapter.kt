package com.example.bookreader.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookreader.Book
import com.example.bookreader.OnClickListenerFav
import com.example.bookreader.R
import kotlinx.android.synthetic.main.book_item.view.*

class FavouritesAdapter(private val fragment: Fragment, val booklist: MutableList<Book>, val onClickListenerFav: OnClickListenerFav):
    RecyclerView.Adapter<FavouritesAdapter.ViewHolderFav>() {
    class ViewHolderFav(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(book: Book){
            Glide.with(itemView).load(book.bookCover).into(itemView.bookCover)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFav {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.book_item_fav, parent, false)
        return ViewHolderFav(view)
    }

    override fun onBindViewHolder(holder: ViewHolderFav, position: Int) {
        holder.bind(booklist[position])
        holder.itemView.setOnClickListener{
            onClickListenerFav.onFavClickListener(position)
        }
    }

    override fun getItemCount(): Int = booklist.size

}