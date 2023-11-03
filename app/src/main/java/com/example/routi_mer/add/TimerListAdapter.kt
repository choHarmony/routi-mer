package com.example.routi_mer.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.GetTimerItemPosition
import com.example.routi_mer.R
import com.example.routi_mer.room.TimerListData

class TimerListAdapter(private val timerList: ArrayList<TimerListData>) :
    RecyclerView.Adapter<TimerListAdapter.ViewHolder>(), ItemTouchHelperListener {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var timerTitle: TextView = itemView.findViewById(R.id.timer_title)
        public var timerDescription: TextView = itemView.findViewById(R.id.timer_description)
        public var timerSec: TextView = itemView.findViewById(R.id.text_second_num)
        public var timerSet: TextView = itemView.findViewById(R.id.text_set_num)
        public var oneSetMusicTitle: TextView = itemView.findViewById(R.id.text_one_set_music)
        public var fullSetMusicTitle: TextView = itemView.findViewById(R.id.text_full_set_music)
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
        holder.oneSetMusicTitle.text = timerList[position].oneSetMusicTitle
        holder.fullSetMusicTitle.text = timerList[position].fullSetMusicTitle

        holder.itemView.findViewById<ImageButton>(R.id.btn_timer_menu).setOnClickListener {
            val TimerBottomSheetFragment = TimerBottomSheetFragment(holder.itemView.context)
            val fm = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
            TimerBottomSheetFragment.show(fm, TimerBottomSheetFragment.tag)

            GetTimerItemPosition.pos = holder.bindingAdapterPosition

            GetTimerItemPosition.timerTitle = timerList[position].timerTitle
            GetTimerItemPosition.timerDes = timerList[position].timerDescription
            GetTimerItemPosition.timerSec = timerList[position].timerSec
            GetTimerItemPosition.timerSet = timerList[position].timerSet
            GetTimerItemPosition.oneSetMusicTitle = timerList[position].oneSetMusicTitle
            GetTimerItemPosition.fullSetMusicTitle = timerList[position].fullSetMusicTitle
        }

    }

    override fun getItemCount(): Int {
        return timerList.size
    }

    override fun onItemMove(from: Int, to: Int) {
        val item: TimerListData = timerList[from]
        timerList.removeAt(from)
        timerList.add(to, item)
        notifyItemMoved(from, to)
    }


}