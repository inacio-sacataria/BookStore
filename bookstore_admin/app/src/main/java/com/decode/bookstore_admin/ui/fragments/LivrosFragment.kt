package com.decode.bookstore_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.adapters.LivrosAdapter
import com.decode.bookstore_admin.databinding.FragmentLivrosBinding
import com.decode.bookstore_admin.ui.viemodel.LivrosViewModel

class LivrosFragment : Fragment() {


    lateinit var binding : FragmentLivrosBinding
    lateinit var adapter: LivrosAdapter;
    lateinit var viewModel: LivrosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_livros, container, false)
        binding = FragmentLivrosBinding.bind(view)

        viewModel = ViewModelProvider(this).get(LivrosViewModel::class.java)

        viewModel.livros.observeForever {
           if (it?.size!=0){
               adapter = LivrosAdapter(requireActivity(),it!!);
               binding.rcvLivrosa.adapter=adapter
               adapter.notifyDataSetChanged()
           }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBooks()
    }
}