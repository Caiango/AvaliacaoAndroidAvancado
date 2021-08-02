package com.example.avaliacaoandroidavancado.ui

import android.app.AlarmManager
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacaoandroidavancado.R
import com.example.avaliacaoandroidavancado.helper.AlarmHelper
import com.example.avaliacaoandroidavancado.model.DatabaseInstance
import com.example.avaliacaoandroidavancado.model.MyNotifications
import com.example.avaliacaoandroidavancado.model.NotificationDao
import com.example.avaliacaoandroidavancado.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notificationViewModel: NotificationViewModel
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db: NotificationDao? = DatabaseInstance.getInstance(this)?.notificationDao
        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        notificationViewModel.getAllNotifications(db!!)
        adapter = NotificationAdapter(this)
        setupRV(rv)

        notificationViewModel.notificationList.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })

        floatAdd.setOnClickListener {
            showDialog(db)
        }

    }

    private fun setupRV(rv: RecyclerView) {
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.setHasFixedSize(true)
        rv.adapter = adapter
    }

    private fun showDialog(db: NotificationDao?) {
        val dialog = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        dialog.setTitle(title)
        dialog.setView(view)
        val title = view.findViewById<EditText>(R.id.edt_title)
        val text = view.findViewById<EditText>(R.id.edt_text)
        val checkRepeat = view.findViewById<CheckBox>(R.id.cbRepeat)
        val timePicker = view.findViewById<TimePicker>(R.id.time)

        timePicker.setIs24HourView(true)

        dialog.setPositiveButton(getString(R.string.add)) { _: DialogInterface, _: Int ->


            val notification = MyNotifications(
                title = title.text.toString(),
                text = text.text.toString(),
                time = "${timePicker.hour}:${timePicker.minute}",
                repeat = checkRepeat.isChecked
            )

            notificationViewModel.insertNotification(notification, db!!)
            AlarmHelper.scheduleRTC(
                this,
                getAlarmManager(),
                timePicker.hour,
                timePicker.minute,
                checkRepeat.isChecked
            )
        }
        dialog.setNegativeButton(getString(R.string.cancel)) { _: DialogInterface, _: Int ->

        }
        dialog.show()
    }

    private fun getAlarmManager(): AlarmManager {
        return getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

}