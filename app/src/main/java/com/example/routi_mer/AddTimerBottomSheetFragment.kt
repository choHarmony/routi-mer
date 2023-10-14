package com.example.routi_mer

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.routi_mer.databinding.BottomSheetAddTimerLayoutBinding


class AddTimerBottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context
    private lateinit var binding: BottomSheetAddTimerLayoutBinding
    private lateinit var ringtone: Ringtone
    private lateinit var fullRingtone: Ringtone

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

        val btnOneSetMusic: Button = view.findViewById(R.id.btn_one_set_music)
        val btnFullSetMusic: Button = view.findViewById(R.id.btn_full_set_music)
        val btnTimerAdd: Button = view.findViewById(R.id.btn_timer_add)
        val btnTimerAddQuit: Button = view.findViewById(R.id.btn_timer_add_quit)


        var oneSetMusicIdx = 0
        var fullSetMusicIdx = 0
        val musicList = mContext.resources.getStringArray(R.array.music_array).toMutableList()

        val rtManager = RingtoneManager(mContext)
        rtManager.setType(RingtoneManager.TYPE_NOTIFICATION)
        rtManager.cursor.run {
            while (moveToNext()) {
                musicList.add(getString(RingtoneManager.TITLE_COLUMN_INDEX))
            }
        }

        btnOneSetMusic.setOnClickListener {
            val builder = AlertDialog.Builder(mContext, R.style.CustomDialogTheme)
            builder.setTitle("한 세트 끝났을 때 효과음 선택")
            builder.setCancelable(false)

            builder.setSingleChoiceItems(musicList.toTypedArray(), oneSetMusicIdx) { dialog, which ->
                oneSetMusicIdx = which
                ringtone = rtManager.getRingtone(oneSetMusicIdx)
                ringtone.stop()
                ringtone.play()
            }

            builder.setPositiveButton("확인") { dialog, which ->
                btnOneSetMusic.text = musicList[oneSetMusicIdx]
                //ringtone.stop()
            }

            builder.setNegativeButton("취소") { dialog, which ->
                //ringtone.stop()
            }

            builder.show()

        }

        btnFullSetMusic.setOnClickListener {
            val builder = AlertDialog.Builder(mContext, R.style.CustomDialogTheme)
            builder.setTitle("세트가 완전히 끝났을 때 효과음 선택")
            builder.setCancelable(false)

            builder.setSingleChoiceItems(musicList.toTypedArray(), fullSetMusicIdx) { dialog, which ->
                fullSetMusicIdx = which
                fullRingtone = rtManager.getRingtone(fullSetMusicIdx)
                fullRingtone.stop()
                fullRingtone.play()
            }

            builder.setPositiveButton("확인") { dialog, which ->
                btnFullSetMusic.text = musicList[fullSetMusicIdx]
                //fullRingtone.stop()
            }

            builder.setNegativeButton("취소") { dialog, which ->
                //fullRingtone.stop()
            }

            builder.show()
        }

        btnTimerAdd.setOnClickListener {
            // timer 추가 액티비티에 새로운 recyclerview 뷰 추가를 위한 데이터 전달
            AddRoutineActivity().receiveData(editTimerTitle.text.toString(), editTimerDes.text.toString(), editTimerSec.text.toString(), editTimerSet.text.toString())

            // db에 새로운 타이머 데이터 추가 동작
            dismiss()
        }

        btnTimerAddQuit.setOnClickListener {
            dismiss()
        }


        return view

    }



}