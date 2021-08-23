package com.vrise.bazaar.newpages.subscriber.addproducts

//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.fragment.app.FragmentActivity
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import com.vrise.R
//import com.vrise.bazaar.newpages.utilities.Interfaces
//import com.vrise.bazaar.newpages.utilities.models.submodels.PrdlistItem

//class ItemList(val activity: FragmentActivity?, val dataItems: ArrayList<PrdlistItem?>?, val subscriberObserver: Interfaces.ReturnId) : RecyclerView.Adapter<ItemList.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        //val imageView7 = itemView.findViewById<ImageView>(R.id.imageView7)
//        val cardView = itemView.findViewById<ConstraintLayout>(R.id.cardView)
//        val textView26 = itemView.findViewById<TextView>(R.id.textView149)
//
//        init {
//            //itemView.findViewById<ImageView>(R.id.floatingActionButton3)!!.visibility = View.GONE
//        }
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_add_products, p0, false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataItems!!.size
//    }
//
//    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//
//        p0.cardView.setOnClickListener {
//            subscriberObserver.returnId(p0.adapterPosition.toString())
//        }
//
////        Picasso.get().load(dataItems!![p0.adapterPosition]!!.imgLink).into(p0.imageView7)
//        p0.textView26.text = dataItems!![p0.adapterPosition]!!.name
//    }
//}