package com.example.bookreader.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.Book
import com.example.bookreader.Catagories
import com.example.bookreader.OnClickListnerHome
import com.example.bookreader.R
import kotlinx.android.synthetic.main.home_row_list.view.*

class HomeAdapterMain(private val fragment: Fragment, val catagories: MutableList<Catagories>, val onClickListnerHome: OnClickListnerHome)
    :RecyclerView.Adapter<HomeAdapterMain.ViewHolderMain>() {
    inner class ViewHolderMain(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(category: Catagories){
            itemView.cat_title!!.text = category.genre
        }
        val itemRecycler:RecyclerView
        init {
            itemRecycler =itemView.findViewById(R.id.rvRowItems)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_row_list,parent,false)
        return  ViewHolderMain(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        holder.bind(catagories[position])
        holder.itemView.cat_title.setOnClickListener {
            onClickListnerHome.onClickListnerOne(position)
        }
        holder.itemView.cat_title.isClickable = false

        setBookItemRecycler(holder.itemRecycler,catagories[position].bookList)
    }

    private fun setBookItemRecycler(recyclerView: RecyclerView, bookList: MutableList<Book>) {
        recyclerView.adapter = HomeBookAdapter(fragment, bookList, onClickListnerHome)
        recyclerView.layoutManager = LinearLayoutManager(fragment.context, RecyclerView.HORIZONTAL,false)




    }

    override fun getItemCount()= catagories.size

}