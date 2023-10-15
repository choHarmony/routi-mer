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
import androidx.recyclerview.widget.RecyclerView.LayoutManager


class TimerBottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context

    lateinit var sendPositionToDeleteListener: SendPositionToDeleteListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        sendPositionToDeleteListener = context as SendPositionToDeleteListener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.bottom_sheet_dialog_layout, container, false)
        val btnTimerEdit: Button = view.findViewById(R.id.btn_edit)
        val btnTimerDelete: Button = view.findViewById(R.id.btn_delete)

        btnTimerEdit.setOnClickListener {
            Toast.makeText(mContext, "타이머 편집", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        btnTimerDelete.setOnClickListener {

            sendPositionToDeleteListener.sendPositionToDelete(getItemPosition.pos)

            dismiss()
        }

        return view

    }

}