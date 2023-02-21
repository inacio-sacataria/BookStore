package com.decode.bookstore_admin.adapters

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.ui.activities.DetailsActivity
import com.decode.bookstore_admin.utils.OBJECT_CLIKED

class LivrosAdapter(var context: Context,
      var books : MutableList<Books>
    ): Adapter<LivrosAdapter.MyLivrosViewHolder>() {

     class MyLivrosViewHolder(item : View): ViewHolder(item){
        var titleView = itemView.findViewById<TextView>(R.id.txtTitle)
        var authorView = itemView.findViewById<TextView>(R.id.txtAuthor)
        var categoriaView = itemView.findViewById<TextView>(R.id.txtCategoria)
        var layoutItem = itemView.findViewById<LinearLayout>(R.id.layoutBookItem)
        var coverUlr = itemView.findViewById<ImageView>(R.id.imgCoverItem)

        fun fillData(books: Books, context: Context){
            titleView.text= books.title
            authorView.text= books.autor
            categoriaView.text= books.categoria
            Glide
                .with(context)
                .load(books.cover)
                .centerCrop()
                .placeholder(R.drawable.cover)
                .into(coverUlr);
            Log.d("broo",books.title.toString())
            layoutItem.setOnClickListener {
                OBJECT_CLIKED.value = books
                var intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("book", books)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLivrosViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.book_list,parent,false)
        return MyLivrosViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyLivrosViewHolder, position: Int) {
       holder.fillData(books.get(position),context)
    }

    override fun getItemCount(): Int {
        return books.size

    }

}