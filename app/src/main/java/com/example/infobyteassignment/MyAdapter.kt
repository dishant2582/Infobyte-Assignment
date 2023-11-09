package com.example.infobyteassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: WatchListFragment, val productArrayList: List<MyDataItem>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_tem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = productArrayList[position]
        holder.htitle.text = currentitem.SYMBOLE
        holder.name.text = currentitem.NAME
        holder.todayop.text = currentitem.TODAY_OPEN.toString()
        holder.low.text = currentitem.ChangeInPRICE.toInt().toString()
        holder.high.text = currentitem.Perc_change.toString()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val htitle = itemView.findViewById<TextView>(R.id.textView)
        val name = itemView.findViewById<TextView>(R.id.textView3)
        val todayop = itemView.findViewById<TextView>(R.id.textView4)
        val low = itemView.findViewById<TextView>(R.id.textView5)
        val high = itemView.findViewById<TextView>(R.id.textView6)

    }

}