package com.computacao.movel.shoplist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.start_screen.*

class startScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        setTitle("Shopping List")

        button.setOnClickListener {
            val intentProcurarListasCriadas = Intent(this, AllShoppingLists::class.java)
            startActivity(intentProcurarListasCriadas)
        }
        button2.setOnClickListener {
            val intentCriarNovaLista = Intent(this, shopListActivity::class.java)
            startActivity(intentCriarNovaLista)
        }
    }
}

