package com.decode.bookstore_admin.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.data.entities.UsersRequest
import com.decode.bookstore_admin.data.repositories.RequestRepositories
import com.decode.bookstore_admin.ui.activities.DetailsActivity
import com.decode.bookstore_admin.utils.OBJECT_CLIKED
import kotlinx.coroutines.GlobalScope

class PedidoAdapter(var context: Context,
                    var requests : MutableList<UsersRequest>
): RecyclerView.Adapter<PedidoAdapter.MyLivrosViewHolder>() {

    class MyLivrosViewHolder(item : View): RecyclerView.ViewHolder(item){
        var titleView = itemView.findViewById<TextView>(R.id.txtTitle)
        var authorView = itemView.findViewById<TextView>(R.id.txtAuthor)
        var categoriaView = itemView.findViewById<TextView>(R.id.txtCategoria)
        var layoutItem = itemView.findViewById<LinearLayout>(R.id.layoutBookItem)
        var coverUlr = itemView.findViewById<ImageView>(R.id.imgCoverItem)
        var nome = itemView.findViewById<TextView>(R.id.txtUserName)
        var email = itemView.findViewById<TextView>(R.id.txtEmail)
        var contacto = itemView.findViewById<TextView>(R.id.txtContacto)
        var estado = itemView.findViewById<TextView>(R.id.txtEstado)

        var aceitar = itemView.findViewById<LinearLayout>(R.id.btnAceitar)
        var rejeitar = itemView.findViewById<LinearLayout>(R.id.btnRejected)

        fun fillData(request:  UsersRequest, context: Context) {
            nome.text = request.readers!!.nome
            email.text = request.readers!!.email
            contacto.text = request.readers!!.contacto
            titleView.text = request.books!!.title
            authorView.text = request.books!!.autor
            categoriaView.text = request.books!!.categoria
            Glide
                .with(context)
                .load(request.books!!.cover)
                .centerCrop()
                .placeholder(R.drawable.cover)
                .into(coverUlr);
            Log.d("broo", request!!.books!!.title.toString())

            aceitar.setOnClickListener {
                request.acceted = true
                RequestRepositories().update(request)
                estado.text = "Aceite"

            }

            if (request.acceted==true){
                estado.text = "Aceite"
            }else{
                estado.text = "Pendente"
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLivrosViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.request,parent,false)
        return MyLivrosViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyLivrosViewHolder, position: Int) {
        var request = requests.get(position)
        holder.fillData(request,context)
        holder.rejeitar.setOnClickListener {
            request.acceted = false
            RequestRepositories().remove(request)
        }
    }

    override fun getItemCount(): Int {
        return requests.size

    }}