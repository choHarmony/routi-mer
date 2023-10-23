package com.example.routi_mer

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.routi_mer.databinding.ActivityAddRoutineBinding
import com.example.routi_mer.databinding.LayoutDialogSetTimerTitleBinding


class EditRoutineTitleDialog(private val context: AppCompatActivity) {

    private lateinit var binding: LayoutDialogSetTimerTitleBinding
    private lateinit var addBinding: ActivityAddRoutineBinding
    private val dlg = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener


    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    fun showDialog(title: String, des: String) {
        binding = LayoutDialogSetTimerTitleBinding.inflate(context.layoutInflater)
        addBinding = ActivityAddRoutineBinding.inflate(context.layoutInflater)

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀바 제거
        dlg.setContentView(binding.root) // 다이얼로그에 사용할 xml 파일 불러오기
        dlg.setCancelable(false) // 다이얼로그 바깥쪽 눌렀을 때 다이얼로그가 꺼지지 않도록
        dlg.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 기본 다이얼로그 배경 투명화
        // 그래서 내가 직접 적용한 둥근 bg가 적용될 수 있도록 만들어줌

        // 제목에 아무것도 없을 경우 빈 edittext로 설정
        if (title == "루틴 이름, 설명, 그룹 설정") {
            binding.editTitle.setText("")
            binding.editDes.setText("")
        }
        else {
            binding.editTitle.setText(title)
            binding.editDes.setText(des)
        }

//        var checkedIdx = 0
//        binding.btnGroup.setOnClickListener {
//            val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)
//            builder.setTitle("그룹 선택")
//            builder.setCancelable(false)
//            builder.setIcon(R.drawable.icon_group_size_edit)
//            val groupList = context.resources.getStringArray(R.array.routine_group)
//
//            builder.setSingleChoiceItems(groupList, checkedIdx) { dialog, which ->
//                checkedIdx = which
//            }
//
//            builder.setPositiveButton("확인") { dialog, which ->
//                binding.btnGroup.text = groupList[checkedIdx]
//            }
//
//            builder.setNegativeButton("취소") { dialog, which ->
//
//            }
//
//            builder.show()
//        }


        binding.btnEditComplete.setOnClickListener {
            if (binding.editTitle.text.isEmpty()) {
                Toast.makeText(context, "루틴 이름은 필수 입력사항입니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                onClickListener.onClicked(binding.editTitle.text.toString(), binding.editDes.text.toString(), "")
                // for group: binding.btnGroup.text.toString()
                dlg.dismiss()
            }
        }

        binding.btnEditQuit.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }



    interface OnDialogClickListener {
        fun onClicked(title: String, des: String, group: String)
    }




}