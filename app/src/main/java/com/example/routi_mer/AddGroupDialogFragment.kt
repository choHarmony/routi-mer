package com.example.routi_mer

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.routi_mer.databinding.BottomSheetAddGroupLayoutBinding
import com.example.routi_mer.databinding.LayoutGroupListBinding


class AddGroupDialogFragment(var adapter: GroupListAdapter) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetAddGroupLayoutBinding
    private var data = mutableListOf<GroupListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bottom_sheet_add_group_layout, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.group_list).adapter = adapter

        binding = BottomSheetAddGroupLayoutBinding.inflate(layoutInflater)

    }
}