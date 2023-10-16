package com.example.routi_mer

import android.annotation.SuppressLint
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.routi_mer.R.id
import com.example.routi_mer.databinding.BottomSheetAddTimerLayoutBinding
import com.example.routi_mer.databinding.BottomSheetEditTimerLayoutBinding


class EditTimerBottomSheetFragment(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context
    private lateinit var binding: BottomSheetEditTimerLayoutBinding
    private lateinit var ringtone: Ringtone
    private lateinit var fullRingtone: Ringtone

    lateinit var sendEventListener: SendEditTimerListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        sendEventListener = context as SendEditTimerListener
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

        binding = BottomSheetEditTimerLayoutBinding.inflate(layoutInflater)

        val view = inflater.inflate(R.layout.bottom_sheet_edit_timer_layout, container, false)
        val editTimerTitle: EditText = view.findViewById(R.id.rewrite_timer_title)
        val editTimerDes: EditText = view.findViewById(R.id.rewrite_timer_des)
        val editTimerSec: EditText = view.findViewById(R.id.rewrite_timer_sec)
        val editTimerSet: EditText = view.findViewById(R.id.rewrite_timer_set)

        val btnReOneSetMusic: Button = view.findViewById(R.id.btn_re_one_set_music)
        val btnReFullSetMusic: Button = view.findViewById(R.id.btn_re_full_set_music)
        val btnTimerEdit: Button = view.findViewById(R.id.btn_timer_edit)
        val btnTimerEditQuit: Button = view.findViewById(R.id.btn_timer_edit_quit)

        editTimerTitle.setText(getItemPosition.timerTitle)
        editTimerDes.setText(getItemPosition.timerDes)
        editTimerSec.setText(getItemPosition.timerSec)
        editTimerSet.setText(getItemPosition.timerSet)
        btnReOneSetMusic.text = getItemPosition.oneSetMusicTitle
        btnReFullSetMusic.text = getItemPosition.fullSetMusicTitle


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

        btnReOneSetMusic.setOnClickListener {
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
                btnReOneSetMusic.text = musicList[oneSetMusicIdx]
                //ringtone.stop()
            }

            builder.setNegativeButton("취소") { dialog, which ->
                //ringtone.stop()
            }

            builder.show()

        }

        btnReFullSetMusic.setOnClickListener {
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
                btnReFullSetMusic.text = musicList[fullSetMusicIdx]
                //fullRingtone.stop()
            }

            builder.setNegativeButton("취소") { dialog, which ->
                //fullRingtone.stop()
            }

            builder.show()
        }

        btnTimerEdit.setOnClickListener {
            // timer 추가 액티비티에 새로운 recyclerview 뷰 추가를 위한 데이터 전달
            sendEventListener.editTimerData(editTimerTitle.text.toString(), editTimerDes.text.toString(), editTimerSec.text.toString(), editTimerSet.text.toString(), btnReOneSetMusic.text.toString(), btnReFullSetMusic.text.toString())

            // editText에 있는 애들 다 초기화
            editTimerTitle.text.clear()
            editTimerDes.text.clear()
            editTimerSec.text.clear()
            editTimerSet.text.clear()

            // db에 새로운 타이머 데이터 추가 동작

            dismiss()
        }

        btnTimerEditQuit.setOnClickListener {
            editTimerTitle.text.clear()
            editTimerDes.text.clear()
            editTimerSec.text.clear()
            editTimerSet.text.clear()

            dismiss()
        }


        return view

    }


}