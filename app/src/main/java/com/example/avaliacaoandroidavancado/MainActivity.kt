package com.example.avaliacaoandroidavancado

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
            dialog.setTitle(title)
            dialog.setView(view)
            val title = view.findViewById<EditText>(R.id.edt_title)
            val text = view.findViewById<EditText>(R.id.edt_text)
            val checkRepeat = view.findViewById<CheckBox>(R.id.cbRepeat)

            dialog.setPositiveButton("Adicionar") { _: DialogInterface, _: Int ->

            }
            dialog.setNegativeButton("Cancelar") { _: DialogInterface, i: Int ->

            }
            dialog.show()
        }
    }
}