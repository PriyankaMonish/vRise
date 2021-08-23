package com.vrise.bazaar.newpages.registration

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.vrise.R
import com.vrise.bazaar.newpages.containers.NotificationContainer
import com.vrise.bazaar.newpages.utilities.HelperMethods.changeDateFormat
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.submodels.NotificationsItem

class NotificationList(val notificationContainer: NotificationContainer, val notifications: ArrayList<NotificationsItem?>, val clickedItem: Interfaces.ClickedItem) : RecyclerView.Adapter<NotificationList.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_notification, p0, false))
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        if (notifications[p0.adapterPosition]!!.status == "0") {
//            p0.view.setBackgroundColor(ContextCompat.getColor(notificationContainer, R.color.green_drawer))
        } else {
//            p0.view.setBackgroundColor(ContextCompat.getColor(notificationContainer, R.color.tall_poppy))
        }

        p0.item.setOnClickListener {
            if (notifications[p0.adapterPosition]!!.status == "1") {
                clickedItem.returnIdValue(p0.adapterPosition.toString(), notifications[p0.adapterPosition]!!.id.toString())
            } else {
                clickedItem.returnIdValue(p0.adapterPosition.toString(), notifications[p0.adapterPosition]!!.id.toString())
            }
        }

        p0.textView48.text = notifications[p0.adapterPosition]!!.message
        p0.textView47.text = notifications[p0.adapterPosition]!!.title
        try {
            p0.textView152.text = changeDateFormat("yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy | hh:mm a", notifications[p0.adapterPosition]!!.notifyOn.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun removeAll(param: Interfaces.ClickedItem) {
        notifications.clear()
        param.returnIdValue("", "")
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layoutChanges = itemView.findViewById<ConstraintLayout>(R.id.layoutChanges)
        val view = itemView.findViewById<LinearLayout>(R.id.view)
        val item = itemView.findViewById<LinearLayout>(R.id.item)
        val textView48 = itemView.findViewById<TextView>(R.id.textView48)
        val textView47 = itemView.findViewById<TextView>(R.id.textView47)
        val textView152 = itemView.findViewById<TextView>(R.id.textView152)
    }

}
