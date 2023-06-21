package com.example.routi_mer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.databinding.RoutineListLayoutBinding

class RoutineListAdapter() :
    RecyclerView.Adapter<RoutineListAdapter.MyViewHolder>() {

    var routineList = ArrayList<RoutineListData>()
    private lateinit var binding: RoutineListLayoutBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var routineTitle: TextView = itemView.findViewById(R.id.routine_title)
        public var routineDescription: TextView = itemView.findViewById(R.id.routine_description)
        public var startBtn: Button = itemView.findViewById(R.id.btn_start)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.routine_list_layout, parent, false)

        return MyViewHolder(cardView)
    }

    // 뷰와 데이터의 결합이 이루어지는 장소. 여기서 for문을 통해 data list에 있는 애들을 불러오면 될 것 같음
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.routineTitle.text = "거북목 스트레칭"
        holder.routineDescription.text = "따라하면 목이 편해지고 머리가 맑아진다"

    }

    override fun getItemCount(): Int {
        //return routineList.size
        return 10
    }

}