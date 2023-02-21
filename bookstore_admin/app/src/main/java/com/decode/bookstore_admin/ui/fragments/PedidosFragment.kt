package com.decode.bookstore_admin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.adapters.PedidoAdapter
import com.decode.bookstore_admin.databinding.FragmentPedidosBinding
import com.decode.bookstore_admin.ui.viemodel.RequestViewModel


class PedidosFragment : Fragment() {
    lateinit var binding : FragmentPedidosBinding
    lateinit var viewModel: RequestViewModel
    lateinit var adapter: PedidoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_pedidos, container, false)
        binding = FragmentPedidosBinding.bind(view)
        viewModel = ViewModelProvider(this).get(RequestViewModel::class.java)

        viewModel.request.observe(viewLifecycleOwner){
            adapter = PedidoAdapter(requireActivity(), it)
            binding.rcvLivrosa.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }
}