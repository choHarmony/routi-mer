package com.example.routi_mer.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.GetRoutineItemPosition
import com.example.routi_mer.R
import com.example.routi_mer.timer.TimerActivity
import com.example.routi_mer.edit.BottomSheetFragment

class RoutineListAdapter(private val routineList: ArrayList<RoutineRecyclerViewData>) :
    RecyclerView.Adapter<RoutineListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var routineTitle: TextView = itemView.findViewById(R.id.routine_title)
        public var routineDescription: TextView = itemView.findViewById(R.id.routine_description)
        var routineGroup: TextView = itemView.findViewById(R.id.routine_group)
        public var startBtn: Button = itemView.findViewById(R.id.btn_start)
        var menuBtn: ImageButton = itemView.findViewById(R.id.btn_menu)
        var groupingBtn: Button = itemView.findViewById(R.id.btn_grouping)


//        private val bottomSheetFragment = BottomSheetFragment(itemView.context)
//
//        init {
//            menuBtn.setOnClickListener {
//                val fManager = (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
//                bottomSheetFragment.show(fManager, bottomSheetFragment.tag)
//            }
//        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.layout_routine_list, parent, false)

        return ViewHolder(cardView)
    }

    // 뷰와 데이터의 결합이 이루어지는 장소
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.routineTitle.text = routineList[position].routineTitle
        holder.routineDescription.text = routineList[position].routineDescription
        holder.routineGroup.text = routineList[position].routineGroup

        holder.menuBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment(holder.itemView.context)
            val fManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
            bottomSheetFragment.show(fManager, bottomSheetFragment.tag)

            GetRoutineItemPosition.routinePos = holder.bindingAdapterPosition
        }

        holder.startBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, TimerActivity::class.java)

            GetRoutineItemPosition.routinePos = holder.bindingAdapterPosition
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return routineList.size
    }


}