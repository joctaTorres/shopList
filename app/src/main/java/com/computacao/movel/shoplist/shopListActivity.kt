package com.computacao.movel.shoplist

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_shop_list.*
import kotlinx.android.synthetic.main.list_row.view.*
import kotlin.collections.HashMap

class shopListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list)
    }

    override fun onResume() {
        super.onResume()

        var listItens = arrayOf(
            hashMapOf(
                "itemName" to "Macarrão",
                "itemPrice" to 3.28f,
                "itemQnt" to 3
            ),
            hashMapOf(
                "itemName" to "Feijão",
                "itemPrice" to 2.48f,
                "itemQnt" to 5
            ),
            hashMapOf(
                "itemName" to "Arroz",
                "itemPrice" to 1.28f,
                "itemQnt" to 2
            )
        )
        itemsList.adapter = ListAdapter(this, listItens)
        var listTotalValue = getTotalValueFromList(listItens)
        listTotal.text = "Total R$ ${listTotalValue}"
    }

    private fun getTotalValueFromList(listItens: Array<HashMap<String, Any>>): Float {
        return listItens.fold(0f) {
                total, item -> total + ((item.get("itemPrice") as Float) * ((item.get("itemQnt") as Int).toFloat()))
        }
    }


    private class ListAdapter(ctx: Context, items: Array<HashMap<String, Any>>) : BaseAdapter() {

        private val context : Context
        private val itemsList : Array<HashMap<String, Any>>


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
