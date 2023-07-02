package com.example.routi_mer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class GroupListAdapter() :
    RecyclerView.Adapter<GroupListAdapter.Holder>() {

    private var groupList: MutableList<GroupListData> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val groupListView = LayoutInflater.from(parent.context).inflate(R.layout.layout_group_list, parent, false)

        return Holder(groupListView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = groupList[position]
        holder.bind(item)

        holder.view.findViewById<Button>(R.id.btn_group_delete).setOnClickListener {
            // roomdb에서도 삭제시키는 코드 추가하는 거 잊지 말기~!
            groupList.remove(groupList[position])
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, groupList.size)
        }
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    fun setItem(items: MutableList<GroupListData>) {
        if (!items.isNullOrEmpty()) {
            groupList = items
            notifyDataSetChanged()
        }
    }

    inner class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: GroupListData) {
            view.findViewById<TextView>(R.id.show_group_name).text = item.groupName
        }
    }


}