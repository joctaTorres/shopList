package com.computacao.movel.shoplist

import java.util.*

class List {
    var Name : String
    var Items : ArrayList<HashMap<String, Any>>
    var TotalPrice : Float = 0.0f
    var shopDate : Date

    constructor(Name: String, Items: ArrayList<HashMap<String, Any>>, TotalPrice: Float) {
        this.Name = Name
        this.Items = Items
        this.TotalPrice = TotalPrice
        this.shopDate = Date()
    }

}