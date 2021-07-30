package com.example.avaliacaoandroidavancado.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacaoandroidavancado.R
import com.example.avaliacaoandroidavancado.databinding.RvItemsBinding
import com.example.avaliacaoandroidavancado.model.MyNotifications

class NotificationAdapter(val context: Context) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    private var notifications: List<MyNotifications> = listOf()

    class ViewHolder(view: RvItemsBinding) : RecyclerView.ViewHolder(view.root) {
        val title = view.txtTitle
        val text = view.txtText
        val repeat = view.txtRepeat
        val time = view.txtTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false)
        val binding = RvItemsBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        val time = notification.time.toString()
        var finalTime = ""
        var hour = ""
        var minute = ""

        holder.title.text = notification.title
        holder.text.text = notification.text
        if (notification.midday && time.length == 4 || !notification.midday && time.length == 4) {
            hour = time.substring(0..1)
            minute = time.substring(2..3)
            finalTime = "$hour:$minute"
            holder.time.text = finalTime
        } else if (notification.midday && time.length == 3 ) {
            hour = time.substring(0..1)
            minute = time.substring(2..2)
            finalTime = "$hour:$minute"
            holder.time.text = finalTime
        } else if (!notification.midday && time.length == 3) {
            hour = time.substring(0..0)
            minute = time.substring(1..2)
            finalTime = "$hour:$minute"
            holder.time.text = finalTime
        } else if(time.length == 2){
            hour = time.substring(0..0)
            minute = time.substring(1..1)
            finalTime = "$hour:$minute"
            holder.time.text = finalTime
        }

        if (notification.repeat) {
            holder.repeat.text = context.getString(R.string.daily)
        } else {
            holder.repeat.text = context.getString(R.string.once)
        }
    }

    fun setList(list: List<MyNotifications>) {
        notifications = list
    }

    override fun getItemCount(): Int = notifications.size
}