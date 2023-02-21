package com.decode.bookstore_admin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.data.entities.Categoria
import com.decode.bookstore_admin.utils.showInfo

class CategoriasAdapter(
    var context: Context,
    var categorias: MutableList<Categoria>
): Adapter<CategoriasAdapter.MyViewHolder>() {

    inner class MyViewHolder(item : View) : ViewHolder(item){
        var tvCategoria = item.findViewById<TextView>(R.id.tv_CategoriaItem)
        var lyt = item.findViewById<LinearLayout>(R.id.lyt_categoria_item)

        fun fillData(categoria: Categoria){
            tvCategoria.setText(categoria.name)
            lyt.setOnClickListener {
                context.showInfo("categoria ${categoria.name}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.categoria_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fillData(categoria = categorias.get(position))
    }

    override fun getItemCount(): Int {
        return categorias.size
    }
}