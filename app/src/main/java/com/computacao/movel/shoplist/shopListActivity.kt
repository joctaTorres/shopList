package com.computacao.movel.shoplist

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_shop_list.*

class shopListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)

        itemsList.adapter = ListAdapter(this)
    }

    fun finshList(view : View) {

    }

    private class ListAdapter(ctx : Context) : BaseAdapter() {

        private val context : Context

        init {
            context = ctx
        }

        // responsible for rendering each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            val row = inflater.inflate(R.layout.list_row, parent, false)
            return row

            // val textView = TextView(context)
            // textView.text = "ITEM TEXT"
            // return textView
        }

        override fun getItem(position: Int): Any {
            return "COOL LIST TEXT"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // responsible for how many rows in list
        override fun getCount(): Int {
            return 10
        }

    }
}
