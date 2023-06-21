package com.example.routi_mer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.routi_mer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar() // toolbar 설정, title 지우기
        binding.btnSettings.setOnClickListener {
            Toast.makeText(this, "setting btn is clicked", Toast.LENGTH_SHORT).show()
        }
        setSpinner() // spinner adapter, selected 요소 관리


    }

    private fun setToolbar() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // toolbar title 제거
    }

    private fun setSpinner() {
        // spinner
        var spinnerGroupData = resources.getStringArray(R.array.routine_group)
        var spinnerAdapter = ArrayAdapter<String>(this, R.layout.spinner_open, spinnerGroupData)
        binding.spinnerGroupSelect.adapter = spinnerAdapter

        binding.spinnerGroupSelect.setSelection(0)
        binding.spinnerGroupSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, "${spinnerGroupData[binding.spinnerGroupSelect.selectedItemPosition]} selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }





//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item!!.itemId) {
//            R.id.btn_setting -> {
//                Toast.makeText(this, "setting btn is clicked", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


}