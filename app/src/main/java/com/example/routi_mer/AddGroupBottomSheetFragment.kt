package com.example.routi_mer

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.routi_mer.databinding.BottomSheetAddGroupLayoutBinding


class AddGroupBottomSheetFragment(var adapter: GroupListAdapter) : BottomSheetDialogFragment() {

    private val data: MutableList<GroupListData> = ArrayList()
    private lateinit var binding: BottomSheetAddGroupLayoutBinding
    var name = ""
    private var groupList: MutableList<GroupListData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BottomSheetAddGroupLayoutBinding.inflate(layoutInflater)
        val view = inflater.inflate(R.layout.bottom_sheet_add_group_layout, container, false)
        val btnAddGroup = view.findViewById<Button>(R.id.btn_add_group)
        btnAddGroup.setOnClickListener {
            val dialog = AddGroupDialog(view.context)
            dialog.myDlg()

            dialog.setOnClickedListener(object : AddGroupDialog.ButtonClickListener {
                override fun onClicked(addedGroupName: String) {
                    // string-array 추가
                    val groupNameList = view.context.resources.getStringArray(R.array.routine_group).toMutableList()
                    groupNameList.add(addedGroupName)
                    name = addedGroupName

                    // recyclerview 추가

//                    // recyclerview 추가
//                    val mAdapter = GroupListAdapter(data)
//                    binding.groupList.adapter = mAdapter
//
//                    val layout = LinearLayoutManager(view.context)
//                    binding.groupList.layoutManager = layout
//                    binding.groupList.setHasFixedSize(true)
//                    mAdapter.addItem(GroupListData(addedGroupName))
                }
            })
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.group_list).adapter = adapter

//        // recyclerview 추가
//        viewManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
//        viewAdapter = GroupListAdapter()
//        recyclerView = binding.groupList.apply {
//            setHasFixedSize(true)
//            layoutManager = viewManager
//            adapter = viewAdapter
//        }
//
//        groupList.apply {
//            add(GroupListData(name))
//            adapter.notifyDataSetChanged()
//        }



//        val mAdapter = GroupListAdapter(data)
//        binding.groupList.adapter = mAdapter
//
//        val layout = LinearLayoutManager(view.context)
//        binding.groupList.layoutManager = layout
//        binding.groupList.setHasFixedSize(true)
//        mAdapter.addItem(GroupListData(name))
    }
}