package com.example.routi_mer

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class BottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context

    lateinit var sendRoutinePositionListener: SendRoutineListPositionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        sendRoutinePositionListener = context as SendRoutineListPositionListener
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
        val btnEdit: Button = view.findViewById(R.id.btn_edit)
        val btnDelete: Button = view.findViewById(R.id.btn_delete)

        btnEdit.setOnClickListener {
            Toast.makeText(mContext, "편집", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        btnDelete.setOnClickListener {
            // recyclerview 삭제를 위해 메인 액티비티에 삭제를 원하는 뷰의 포지션 전달
            sendRoutinePositionListener.sendRoutinePos(GetRoutineItemPosition.routinePos)

            val routineDB = RoutineDB.getRoutineList(mContext)
            if (routineDB != null) {
                val allRoutine = routineDB.RoutineListDao().selectAllRoutine()

                routineDB.RoutineListDao().deleteRoutine(allRoutine[GetRoutineItemPosition.routinePos])

            }

            dismiss()
        }

        return view

    }
}