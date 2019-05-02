package com.computacao.movel.shoplist

import java.util.*
import kotlin.collections.HashMap

class List {
    var name : String
    var items : ArrayList<HashMap<String, Any>>
    var totalPrice : Float = 0.0f
    var shopDate : String

    constructor(name: String, items: ArrayList<HashMap<String, Any>>, totalPrice: Float, shopDate: String) {
        this.name = name
        this.items = items
        this.totalPrice = totalPrice
        this.shopDate = shopDate
    }

    constructor() : this("", arrayListOf<HashMap<String, Any>>(), 0f, "") {}

}