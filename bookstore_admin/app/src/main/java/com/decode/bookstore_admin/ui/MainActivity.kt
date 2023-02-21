package com.decode.bookstore_admin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.databinding.ActivityMainBinding
import com.decode.bookstore_admin.ui.activities.RegistBookActivity

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostController.navController

        binding.bottomAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.m_pedidos -> setCurrentFragment(R.id.pedidosFragement)
              //  R.id.m_leitores -> setCurrentFragment(R.id.leitoresFragments)
                R.id.m_categorias -> setCurrentFragment(R.id.categoriaFragment)
                R.id.m_livros -> setCurrentFragment(R.id.livrosFragment)
            }
            return@setOnMenuItemClickListener true
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this,RegistBookActivity::class.java))
        }
    }

    private fun setCurrentFragment(fragmentID: Int) {
        navController.navigate(fragmentID)
    }
}