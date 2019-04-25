package com.computacao.movel.shoplist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shop_list.*
import kotlinx.android.synthetic.main.list_row.view.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class shopListActivity : AppCompatActivity() {

    private val ITEM_REQUEST_CODE = 1
    private var listItens = ArrayList<HashMap<String, Any>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
        setTitle("Criando uma lista")

        addItemButton.setOnClickListener {
            var intent = Intent(this, addItemActivity::class.java)
            startActivityForResult(intent, ITEM_REQUEST_CODE)
        }
    }

    override fun onResume() {
        super.onResume()

        itemsList.adapter = ListAdapter(this, listItens)
        var listTotalValue = getTotalValueFromList(listItens)
        listTotal.text = "Total R$ ${listTotalValue}"
    }

    private fun getTotalValueFromList(listItens: ArrayList<HashMap<String, Any>>): Float {
        return listItens.fold(0f) {
                total, item -> total + ((item.get("itemPrice") as Float) * ((item.get("itemQnt") as Int).toFloat()))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            ITEM_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val item = data.extras.get(addItemActivity.ITEM_RESULT_EXTRA)
                    listItens.add(item as HashMap<String, Any>)
                }
            }

            else -> {
                Toast.makeText(this, "Requisição não conhecida", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private class ListAdapter(ctx: Context, items: ArrayList<HashMap<String, Any>>) : BaseAdapter() {

        private val context : Context
        private val itemsList : ArrayList<HashMap<String, Any>>


        init {
            context = ctx
            itemsList = items
        }

        // responsible for rendering each row
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            val row = inflater.inflate(R.layout.list_row, parent, false)

            val itemMap = getItem(position) as Map<String, Any>
            val price = itemMap.get("itemPrice") as Float
            val quantity = itemMap.get("itemQnt") as Int
            val totalItem = price * quantity.toFloat()

            row.itemName.text = "${itemMap.get("itemName")}"
            row.itemPrice.text = "Preço R$${price}"
            row.itemQnt.text = "Quantidade ${quantity}"
            row.itemTotal.text = "Total R$ ${totalItem}"

            return row
        }

        override fun getItem(position: Int): Any {
            return  itemsList.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // responsible for how many rows in list
        override fun getCount(): Int {
            return itemsList.size
        }

    }
}
