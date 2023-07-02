package com.example.routi_mer

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddGroupDialog(context: Context) {

    private val dialog = Dialog(context)

    fun myDlg() {
        dialog.setContentView(R.layout.layout_dialog_add_group)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val addGroupName = dialog.findViewById<EditText>(R.id.add_group_name)
        val btnComplete = dialog.findViewById<Button>(R.id.btn_group_add_complete)
        val btnQuit = dialog.findViewById<Button>(R.id.btn_group_add_quit)

        btnComplete.setOnClickListener {
            if (addGroupName.text.isNullOrEmpty()) {
                Toast.makeText(dialog.context, "추가할 그룹 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                onClickedListener.onClicked(addGroupName.text.toString())
                dialog.dismiss()
            }
        }

        btnQuit.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(addedGroupName: String)
    }

    private lateinit var onClickedListener: ButtonClickListener

    fun setOnClickedListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }

}