package com.example.routi_mer

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.routi_mer.databinding.ActivityAddRoutineBinding
import com.example.routi_mer.databinding.BottomSheetAddTimerLayoutBinding


class AddTimerBottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context
    private lateinit var binding: BottomSheetAddTimerLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BottomSheetAddTimerLayoutBinding.inflate(layoutInflater)

        val view = inflater.inflate(R.layout.bottom_sheet_add_timer_layout, container, false)
        val editTimerTitle: EditText = view.findViewById(R.id.edit_timer_title)
        val editTimerDes: EditText = view.findViewById(R.id.edit_timer_des)
        val editTimerSec: EditText = view.findViewById(R.id.edit_timer_sec)
        val editTimerSet: EditText = view.findViewById(R.id.edit_timer_set)



        return view

    }



}