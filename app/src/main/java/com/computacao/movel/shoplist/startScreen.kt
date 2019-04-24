package com.computacao.movel.shoplist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*

class startScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        button.setOnClickListener {
            val intentProcurarListasCriadas = Intent(this, startScreen::class.java) /*Trocar activity*/
            startActivity(intentProcurarListasCriadas)
        }
        button2.setOnClickListener {
            val intentCriarNovaLista = Intent(this, startScreen::class.java)       /*Trocar activity*/
            startActivity(intentCriarNovaLista)
        }
    }
}

