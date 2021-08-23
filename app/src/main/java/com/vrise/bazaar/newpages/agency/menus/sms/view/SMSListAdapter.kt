package com.vrise.bazaar.newpages.agency.menus.sms.view

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.vrise.R
import com.vrise.bazaar.newpages.utilities.Interfaces
import com.vrise.bazaar.newpages.utilities.models.submodels.SmsPacksItem


class SMSListAdapter(val activity: FragmentActivity, val dataItem: ArrayList<SmsPacksItem?>?, val returnId: Interfaces.ReturnId) : RecyclerView.Adapter<SMSListAdapter.ViewHolder>() {

    var gradientList: HashMap<Int, String>? = null
    var designtList: ArrayList<Int>

    init {
        gradientList = object : HashMap<Int, String>() {
            init {
                put(0, "#67b3ef,#7689ff")
                put(1, "#23eedb,#4eeac3")
                put(2, "#ff9cd3,#ff7b96")
            }
        }
        designtList = object : ArrayList<Int>() {
            init {
                add(R.drawable.sms_1)
                add(R.drawable.sms_2)
                add(R.drawable.sms_3)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView173 = itemView.findViewById<TextView>(R.id.textView173)
        val textView174 = itemView.findViewById<TextView>(R.id.textView174)
        val textView175 = itemView.findViewById<TextView>(R.id.textView175)
        val textView176 = itemView.findViewById<TextView>(R.id.textView176)
        val main_layout = itemView.findViewById<ConstraintLayout>(R.id.main_layout)
        val textView177 = itemView.findViewById<TextView>(R.id.textView177)
        val textView184 = itemView.findViewById<ProgressBar>(R.id.textView184)

        init {
            textView184.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_sms_list, p0, false))
    }

    override fun getItemCount(): Int {
        return dataItem!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.textView173.text = dataItem!![p0.adapterPosition]?.name
        p0.textView175.text = dataItem[p0.adapterPosition]?.description
        p0.textView176.text = "${dataItem[p0.adapterPosition]?.credit} SMS"
        p0.textView177.setOnClickListener {
            p0.textView184.visibility = View.VISIBLE
            Handler().postDelayed(
                    {
                        p0.textView184.visibility = View.GONE
                    }, 1000
            )
        }
        p0.textView174.text = activity.getString(R.string.rupees) + " " + dataItem[p0.adapterPosition]?.price!!


        val randId = getRandId(p0.adapterPosition)
        println("${p0.adapterPosition} :  $randId")
        val gradient = gradientList!![randId]


        p0.main_layout.setBackgroundResource(designtList[randId])
        val paint = p0.textView174.paint
        val width = paint.measureText(dataItem[p0.adapterPosition]?.price)
        val textShader = LinearGradient(0f, 0f, width, p0.textView174.textSize, intArrayOf(Color.parseColor(gradient!!.split(",")[0]), Color.parseColor(gradient.split(",")[1])), null, Shader.TileMode.CLAMP)
        p0.textView174.getPaint().setShader(textShader)
        p0.textView174.setTextColor(Color.parseColor(gradient.split(",")[0]))

    }

    fun getRandId(pos: Int): Int {
        /*Random.nextInt(this.gradientList!!.size)*/
        return when (pos % 3) {
            0 -> 0
            2 -> 2
            1 -> 1
            else -> 0
        }
    }
}
