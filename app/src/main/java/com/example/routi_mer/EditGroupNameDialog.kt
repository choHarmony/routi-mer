package com.example.routi_mer

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditGroupNameDialog(context: Context) {

    private val dialog = Dialog(context)

    fun myDlg() {
        dialog.setContentView(R.layout.layout_dialog_edit_group_name)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val editGroupName = dialog.findViewById<EditText>(R.id.edit_group_name)
        val btnComplete = dialog.findViewById<Button>(R.id.btn_group_edit_complete)
        val btnQuit = dialog.findViewById<Button>(R.id.btn_group_edit_quit)

        btnComplete.setOnClickListener {
            if (editGroupName.text.isNullOrEmpty()) {
                Toast.makeText(dialog.context, "변경할 그룹 이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                onClickedListener.onClicked(editGroupName.text.toString())
                dialog.dismiss()
            }
        }

        btnQuit.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    interface ButtonClickListener {
        fun onClicked(editedGroupName: String)
    }

    private lateinit var onClickedListener: ButtonClickListener

    fun setOnClickedListener(listener: ButtonClickListener) {
        onClickedListener = listener
    }

}