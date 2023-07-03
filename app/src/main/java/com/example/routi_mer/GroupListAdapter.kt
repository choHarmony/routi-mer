package com.example.routi_mer

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class GroupListAdapter(var groupList: MutableList<GroupListData> = ArrayList()) :
    RecyclerView.Adapter<GroupListAdapter.Holder>() {

    //private var groupList: MutableList<GroupListData> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val groupListView = LayoutInflater.from(parent.context).inflate(R.layout.layout_group_list, parent, false)

        return Holder(groupListView)
    }

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val item = groupList[position]
        holder.bind(item)

        holder.view.findViewById<Button>(R.id.btn_group_edit).setOnClickListener {
            val dialog = EditGroupNameDialog(holder.view.context)
            dialog.myDlg()

            dialog.setOnClickedListener(object : EditGroupNameDialog.ButtonClickListener {
                override fun onClicked(editedGroupName: String) {
                    // roomdb에서도 편집하는 코드 추가하는 거 잊지 말기~!
                    groupList[position].groupName = editedGroupName
                    notifyItemChanged(position)
                }
            })
        }

        holder.view.findViewById<Button>(R.id.btn_group_delete).setOnClickListener {
            val builder = AlertDialog.Builder(holder.view.context, R.style.CustomDialogTheme)
            builder.setTitle("삭제 확인")
            builder.setMessage("정말 삭제하시겠습니까?")
            builder.setCancelable(false)

            builder.setPositiveButton("확인") { dialog, which ->
                // roomdb에서도 삭제시키는 코드 추가하는 거 잊지 말기~!
                groupList.remove(groupList[position])
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, groupList.size)
            }

            builder.setNegativeButton("취소") { dialog, which ->

            }

            builder.show()

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