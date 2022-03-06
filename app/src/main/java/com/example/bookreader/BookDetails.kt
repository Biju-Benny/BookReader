package com.example.bookreader

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetails : AppCompatActivity() {
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val bookName: String = intent.getStringExtra("BookName").toString()
        val author: String = intent.getStringExtra("Author").toString()
        val bookCover: String = intent.getStringExtra("BookCover").toString()
        val content: String =intent.getStringExtra("Content").toString()
        val genre: String = intent.getStringExtra("Genre").toString()
        val bookUid: String = intent.getStringExtra("uid").toString()
        val uidCurrent = FirebaseAuth.getInstance().uid ?: ""

        val favRef = db.collection("users").document(uidCurrent).collection("myFavourites").document(bookUid)
        favRef.get().addOnSuccessListener { documentSnapshot ->
            var bookFav: Book? = documentSnapshot.toObject<Book>()
            if (bookFav != null){
                buttonFav.text = "added to favourites"
                buttonFav.isEnabled= false
                buttonFav.setBackgroundColor(Color.GREEN)
                buttonFav.setTextColor(Color.WHITE)

            }

        }





        Glide.with(this).load(bookCover).into(bookCoverBd)
        bookNameBd.text = bookName
        authorBd.text = author
        contentBd.text = content


        buttonFav.setOnClickListener{
            val bookFav = Book(bookName,author,genre,bookCover,content,bookUid)
            db.collection("users").document(uidCurrent).collection("myFavourites").document(bookUid)
                .set(bookFav)
                .addOnCompleteListener {
                    //Log.d(TAG,"User saved to database")
                    buttonFav.text = "added to favourites"
                    buttonFav.isEnabled= false
                    buttonFav.setBackgroundColor(Color.GREEN)

                }
                .addOnFailureListener {
                    //Log.d(TAG, "Failed to set value to database")
                }





        }
    }
}