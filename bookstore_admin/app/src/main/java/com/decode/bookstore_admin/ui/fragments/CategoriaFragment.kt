package com.decode.bookstore_admin.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.adapters.CategoriasAdapter
import com.decode.bookstore_admin.adapters.LivrosAdapter
import com.decode.bookstore_admin.databinding.FragmentCategoriaBinding
import com.decode.bookstore_admin.ui.activities.RegistCategoryActivity
import com.decode.bookstore_admin.ui.viemodel.CategoriaViewModel
import com.decode.bookstore_admin.ui.viemodel.LivrosViewModel


class CategoriaFragment : Fragment() {

    lateinit var binding : FragmentCategoriaBinding
    lateinit var viewModel: CategoriaViewModel
    lateinit var adapter: CategoriasAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_categoria, container, false)
        binding = FragmentCategoriaBinding.bind(view)
        viewModel = ViewModelProvider(this).get(CategoriaViewModel::class.java)

        viewModel.categorias.observeForever {
            if (it?.size!=0){
                adapter = CategoriasAdapter(requireActivity(),it!!);
                binding.rcvCategoria.adapter=adapter
                adapter.notifyDataSetChanged()
            }
        }

        binding.fabAddCategoria.setOnClickListener {
            startActivity(Intent(requireActivity(),RegistCategoryActivity::class.java))
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCategoria()
    }
}