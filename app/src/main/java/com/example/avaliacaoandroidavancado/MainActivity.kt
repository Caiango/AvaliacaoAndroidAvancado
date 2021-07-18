package com.example.avaliacaoandroidavancado

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.avaliacaoandroidavancado.model.DatabaseInstance
import com.example.avaliacaoandroidavancado.model.MyNotifications
import com.example.avaliacaoandroidavancado.model.NotificationDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notificationViewModel: NotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db: NotificationDao? = DatabaseInstance.getInstance(this)?.notificationDao
        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)

        floatAdd.setOnClickListener {
            showDialog(db)
        }

    }

    private fun showDialog(db: NotificationDao?) {
        val dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        dialog.setTitle(title)
        dialog.setView(view)
        val title = view.findViewById<EditText>(R.id.edt_title)
        val text = view.findViewById<EditText>(R.id.edt_text)
        val checkRepeat = view.findViewById<CheckBox>(R.id.cbRepeat)

        dialog.setPositiveButton("Adicionar") { _: DialogInterface, _: Int ->
            //mockando valores
            val notification = MyNotifications(
                title = title.text.toString(),
                text = text.text.toString(),
                time = "12:30",
                repeat = checkRepeat.isChecked
            )

            notificationViewModel.insertNotification(notification, db!!)
        }
        dialog.setNegativeButton("Cancelar") { _: DialogInterface, i: Int ->

        }
        dialog.show()
    }
}