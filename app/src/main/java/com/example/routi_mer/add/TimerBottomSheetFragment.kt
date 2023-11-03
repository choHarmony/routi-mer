package com.example.routi_mer.add

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.routi_mer.GetTimerItemPosition
import com.example.routi_mer.R
import com.example.routi_mer.SendPositionToDeleteListener
import com.example.routi_mer.edit.EditTimerBottomSheetFragment


class TimerBottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context
    private val editTimerBTSFragment = EditTimerBottomSheetFragment(mContext)
    val fm = (mContext as AppCompatActivity).supportFragmentManager.beginTransaction()

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
            editTimerBTSFragment.show(fm, editTimerBTSFragment.tag)
            dismiss()
        }

        btnTimerDelete.setOnClickListener {

            sendPositionToDeleteListener.sendPositionToDelete(GetTimerItemPosition.pos)

            dismiss()
        }

        return view

    }

}