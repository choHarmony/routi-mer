package com.example.routi_mer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.databinding.RoutineListLayoutBinding

class RoutineListAdapter(private val routineList: ArrayList<RoutineListData>) :
    RecyclerView.Adapter<RoutineListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public var routineTitle: TextView = itemView.findViewById(R.id.routine_title)
        public var routineDescription: TextView = itemView.findViewById(R.id.routine_description)
        public var startBtn: Button = itemView.findViewById(R.id.btn_start)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.routine_list_layout, parent, false)

        return ViewHolder(cardView)
    }

    // 뷰와 데이터의 결합이 이루어지는 장소. 여기서 for문을 통해 data list에 있는 애들을 불러오면 될 것 같음
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.routineTitle.text = routineList[position].routineTitle
        holder.routineDescription.text = routineList[position].routineDescription
    }

    override fun getItemCount(): Int {
        return routineList.size
    }

}