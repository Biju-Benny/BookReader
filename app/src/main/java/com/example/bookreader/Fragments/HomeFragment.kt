package com.example.bookreader.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.*
import com.example.bookreader.Adapters.HomeAdapterMain
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(),OnClickListnerHome {

    private lateinit var firebaseDb: FirebaseFirestore
    private var booksAdventure: MutableList<Book> = mutableListOf()
    private var booksScifi: MutableList<Book> = mutableListOf()
    private var booksThriller: MutableList<Book> = mutableListOf()
    private var bookCatagories: MutableList<Catagories> = mutableListOf()
    private lateinit var adapter: HomeAdapterMain




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        firebaseDb = FirebaseFirestore.getInstance()
        val booksRefAdventure = firebaseDb.collection("books")
            .whereEqualTo("genre","Adventure")
        booksRefAdventure.addSnapshotListener{snapshot, exception ->
            if (exception != null || snapshot == null){
                return@addSnapshotListener
            }
            var advAry = snapshot.toObjects(Book::class.java)
            booksAdventure.clear()
            booksAdventure.addAll(advAry)
            adapter.notifyDataSetChanged()

        }
        val booksRefScifi = firebaseDb.collection("books")
            .whereEqualTo("genre","Sci fi")
        booksRefScifi.addSnapshotListener{snapshot, exception ->
            if (exception != null || snapshot == null){
                return@addSnapshotListener
            }
            var scifiAry = snapshot.toObjects(Book::class.java)
            booksScifi.clear()
            booksScifi.addAll(scifiAry)
            adapter.notifyDataSetChanged()

        }
        val booksRefThriller = firebaseDb.collection("books")
            .whereEqualTo("genre","Thriller")
        booksRefThriller.addSnapshotListener{snapshot, exception ->
            if (exception != null || snapshot == null){
                return@addSnapshotListener
            }
            var scifiAry = snapshot.toObjects(Book::class.java)
            booksThriller.clear()
            booksThriller.addAll(scifiAry)
            adapter.notifyDataSetChanged()

        }

        bookCatagories.clear()
        bookCatagories.add(Catagories("Adventure:",booksAdventure))
        bookCatagories.add(Catagories("Sci Fi:",booksScifi))
        bookCatagories.add(Catagories("Thriller:",booksThriller))


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        view.rvHomeMain.layoutManager = layoutManager
        adapter = HomeAdapterMain(this,bookCatagories!!, this)
        view.rvHomeMain.adapter = adapter

        return view
    }

    override fun onClickListnerOne(position: Int) {

    }

    override fun onClickListnerTwo(book: Book) {
        val intent = Intent(context,BookDetails::class.java)
        intent.putExtra("BookName",book.bookName)
        intent.putExtra("Author",book.author)
        intent.putExtra("BookCover",book.bookCover)
        intent.putExtra("Content", book.content)
        startActivity(intent)

    }

}