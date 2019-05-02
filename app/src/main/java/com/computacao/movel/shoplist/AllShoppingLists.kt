package com.computacao.movel.shoplist

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all_shopping_lists.*
import kotlinx.android.synthetic.main.shopping_overview.view.*

class AllShoppingLists : AppCompatActivity() {

    lateinit var listShops : MutableList<List?>
    val storage : DatabaseReference = FirebaseDatabase.getInstance().getReference("Lists")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_shopping_lists)
        setTitle("Listas de compras")

        listShops = mutableListOf()

        storage.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                if (data!!.exists()) {
                    listShops.addAll(
                        data.children.map {
                            it.getValue(List::class.java)
                        }
                    )
                }
                shopLists.adapter = ListAdapter(applicationContext, listShops)
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })

    }

    override fun onResume() {
        super.onResume()

        shopLists.adapter = ListAdapter(this, listShops)
    }

    private class ListAdapter(ctx: Context, items: MutableList<List?>) : BaseAdapter() {
        private val context : Context
        private val itemsList : MutableList<List?>


        init {
            context = ctx
            itemsList = items
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            val row = inflater.inflate(R.layout.shopping_overview, parent, false)

            val itemList : List = getItem(position)

            row.listSize.text = "Quantidade de Itens ${itemList.items.size}"
            row.listPrice.text = "Valor Total: R$ ${itemList.totalPrice}"
            row.listName.text = itemList.name as String
            row.shopDate.text = itemList.shopDate as String

            return row
        }

        override fun getItem(position: Int): List {
            return itemsList.get(position)!!
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return itemsList.size
        }

    }
}

