package com.example.routi_mer.add

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.routi_mer.main.GroupListAdapter
import com.example.routi_mer.main.GroupListData
import com.example.routi_mer.R
import com.example.routi_mer.databinding.BottomSheetAddGroupLayoutBinding


class AddGroupBottomSheetFragment(var adapter: GroupListAdapter) : BottomSheetDialogFragment() {

    private val data: MutableList<GroupListData> = ArrayList()
    private lateinit var binding: BottomSheetAddGroupLayoutBinding
    lateinit var recyclerView: RecyclerView


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

        val groupNameList = view.context.resources.getStringArray(R.array.routine_group).toMutableList()
        data.apply {
            // 나중에는 groupNameList를 roomdb에서 받아오는 거로 변경하면 될듯!
            for (i in 1 until groupNameList.size) {
                add(GroupListData(groupNameList[i]))
            }

        }


        btnAddGroup.setOnClickListener {
            val dialog = AddGroupDialog(view.context)
            dialog.myDlg()

            dialog.setOnClickedListener(object : AddGroupDialog.ButtonClickListener {
                override fun onClicked(addedGroupName: String) {
                    // string-array 추가
                    groupNameList.add(addedGroupName)
                    data.add(GroupListData(addedGroupName))

                    // recyclerview 추가
                    recyclerView = view.findViewById(R.id.group_list) as RecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = GroupListAdapter(data)

                    val mAdapter = GroupListAdapter(data)
                    binding.groupList.adapter = mAdapter
                    mAdapter.setItem(data)

                }
            })
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.group_list).adapter = adapter
    }
}