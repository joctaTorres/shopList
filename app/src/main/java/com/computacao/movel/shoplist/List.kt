package com.computacao.movel.shoplist

class List {
    var Name : String
    var Items : ArrayList<HashMap<String, Any>>
    var TotalPrice : Float = 0.0f

    constructor(Name: String, Items: ArrayList<HashMap<String, Any>>, TotalPrice: Float) {
        this.Name = Name
        this.Items = Items
        this.TotalPrice = TotalPrice
    }

}