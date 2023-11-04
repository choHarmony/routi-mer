package com.example.routi_mer.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.routi_mer.GetRoutineItemPosition
import com.example.routi_mer.R
import com.example.routi_mer.room.RoutineDB
import com.example.routi_mer.SendRoutineListPositionListener


class BottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context

    lateinit var sendRoutinePositionListener: SendRoutineListPositionListener
    //lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

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
            val intent = Intent(mContext, EditRoutineActivity::class.java)
            startActivity(intent)

            dismiss()
        }



        btnDelete.setOnClickListener {

            val routineDB = RoutineDB.getRoutineList(mContext)
            if (routineDB != null) {
                val allRoutine = routineDB.RoutineListDao().selectAllRoutine()
                val builder = AlertDialog.Builder(mContext)
                builder.setTitle("삭제 확인")
                builder.setMessage("삭제한 뒤에는 복구할 수 없습니다.\n 그래도 삭제하시겠습니까?")
                builder.setCancelable(false)

                builder.setPositiveButton("삭제") { dialog, which ->
                    // recyclerview 삭제를 위해 메인 액티비티에 삭제를 원하는 뷰의 포지션 전달
                    sendRoutinePositionListener.sendRoutinePos(GetRoutineItemPosition.routinePos)

                    routineDB.RoutineListDao().deleteRoutine(allRoutine[GetRoutineItemPosition.routinePos])
                    dismiss()
                }

                builder.setNegativeButton("취소") { dialog, which ->

                }

                builder.show()

            }

        }

        return view

    }
}