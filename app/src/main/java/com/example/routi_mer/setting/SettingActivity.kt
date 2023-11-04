package com.example.routi_mer.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.routi_mer.R
import com.example.routi_mer.databinding.ActivitySettingBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpenSource.setOnClickListener {
            OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
            startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        }

        binding.btnFontOpenSource.setOnClickListener {
            val intent = Intent(this, SourceActivity::class.java)
            startActivity(intent)
        }
    }
}