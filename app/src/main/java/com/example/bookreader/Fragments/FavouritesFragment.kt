package com.example.bookreader.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.Adapters.FavouritesAdapter
import com.example.bookreader.Book
import com.example.bookreader.BookDetails
import com.example.bookreader.OnClickListenerFav
import com.example.bookreader.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_favourites.view.*


class FavouritesFragment : Fragment(),OnClickListenerFav {

    private var favList : MutableList<Book> = mutableListOf()
    private lateinit var firebaseDb: FirebaseFirestore
    private lateinit var adapter: FavouritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firebaseDb = FirebaseFirestore.getInstance()
        val uidCurrent = FirebaseAuth.getInstance().uid ?: ""
        val favRef = firebaseDb.collection("users").document(uidCurrent).collection("myFavourites")
        favRef.addSnapshotListener{snapshot, exception ->
            if (exception != null || snapshot == null){
                return@addSnapshotListener
            }
            var favAry = snapshot.toObjects(Book::class.java)
            favList.clear()
            favList.addAll(favAry)
            adapter.notifyDataSetChanged()


        }


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity,2)
        view.rvFavourites.layoutManager = layoutManager
        adapter = FavouritesAdapter(this, favList,this)
        view.rvFavourites.adapter = adapter

        return view
    }

    override fun onFavClickListener(position: Int) {
        val intent = Intent(context, BookDetails::class.java)
        intent.putExtra("BookName",favList[position].bookName)
        intent.putExtra("Author",favList[position].author)
        intent.putExtra("BookCover",favList[position].bookCover)
        intent.putExtra("Content", favList[position].content)
        intent.putExtra("Genre",favList[position].genre)
        intent.putExtra("uid",favList[position].uid)
        startActivity(intent)
    }

}