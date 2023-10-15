package com.example.routi_mer

import android.app.ProgressDialog.show
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class TimerListAdapter(private val timerList: ArrayList<TimerListData>) :
    RecyclerView.Adapter<TimerListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var timerTitle: TextView = itemView.findViewById(R.id.timer_title)
        public var timerDescription: TextView = itemView.findViewById(R.id.timer_description)
        public var timerSec: TextView = itemView.findViewById(R.id.text_second_num)
        public var timerSet: TextView = itemView.findViewById(R.id.text_set_num)
        private var timerMenuBtn: ImageButton = itemView.findViewById(R.id.btn_timer_menu)

        private val TimerBottomSheetFragment = TimerBottomSheetFragment(itemView.context)

//        init {
//            timerMenuBtn.setOnClickListener {
//                val fm = (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
//                TimerBottomSheetFragment.show(fm, TimerBottomSheetFragment.tag)
//
//            }
//
//
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val timerCardView = LayoutInflater.from(parent.context).inflate(R.layout.layout_timer_list, parent, false)

        return ViewHolder(timerCardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timerTitle.text = timerList[position].timerTitle
        holder.timerDescription.text = timerList[position].timerDescription
        holder.timerSec.text = timerList[position].timerSec
        holder.timerSet.text = timerList[position].timerSet

        holder.itemView.findViewById<ImageButton>(R.id.btn_timer_menu).setOnClickListener {
            val TimerBottomSheetFragment = TimerBottomSheetFragment(holder.itemView.context)
            val fm = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
            TimerBottomSheetFragment.show(fm, TimerBottomSheetFragment.tag)
            getItemPosition.pos = holder.bindingAdapterPosition
        }

    }

    override fun getItemCount(): Int {
        return timerList.size
    }



}