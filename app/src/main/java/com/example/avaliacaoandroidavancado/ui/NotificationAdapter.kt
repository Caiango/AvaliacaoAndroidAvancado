package com.example.avaliacaoandroidavancado.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avaliacaoandroidavancado.R
import com.example.avaliacaoandroidavancado.databinding.RvItemsBinding
import com.example.avaliacaoandroidavancado.model.MyNotifications

class NotificationAdapter :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    private lateinit var notifications: List<MyNotifications>

    class ViewHolder(view: RvItemsBinding) : RecyclerView.ViewHolder(view.root) {
        val title = view.txtTitle
        val text = view.txtText
        val repeat = view.txtRepeat
        val time = view.txtxTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_items, parent, false)
        val binding = RvItemsBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]

        holder.title.text = notification.title
        holder.text.text = notification.text
        holder.time.text = notification.time
        holder.repeat.text = "Diário"
    }

    fun setList(list: List<MyNotifications>) {
        notifications = list
    }


    override fun getItemCount(): Int = notifications.size
}