package com.computacao.movel.shoplist

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.add_item.*

class addItemActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE = 0
    companion object {
        val ITEM_RESULT_EXTRA : String = "itemMapResult"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
        setTitle("Adcionando um Item")

        cameraBtn.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (cameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
            }
        }

        addItemButton.setOnClickListener {
            if (!itemName.text.isBlank() && !itemPrice.text.isBlank() && !itemQnt.text.isBlank()) {
                assembleItemAndFinish()
            } else {
                Toast.makeText(this, "Preencha os campos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun assembleItemAndFinish() {
        val priceValue = itemPrice.text.toString().toFloat()
        val quantityValue = itemQnt.text.toString().toInt()

        var addedItem = hashMapOf(
            "itemName" to itemName.text.toString(),
            "itemPrice" to priceValue,
            "itemQnt" to quantityValue
        )

        val addItemIntent = Intent()
        addItemIntent.putExtra(ITEM_RESULT_EXTRA, addedItem)

        setResult(Activity.RESULT_OK, addItemIntent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    groceryImage.setImageBitmap(
                        data.extras.get("data") as Bitmap
                    )
                }
            }

           else -> {
               Toast.makeText(this, "Requisição não conhecida", Toast.LENGTH_SHORT).show()
           }
        }
    }
}
