package com.zycom.suitmediasubmission.ui.screen.second

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zycom.suitmediasubmission.ui.screen.main.MainActivity
import com.zycom.suitmediasubmission.ui.MainApplication
import com.zycom.suitmediasubmission.ui.screen.third.ThirdActivity
import com.zycom.suitmediasubmission.data.factory.ViewModelFactory
import com.zycom.suitmediasubmission.data.response.DataItem
import com.zycom.suitmediasubmission.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val viewModel: SecondViewModel by viewModels(factoryProducer = {
        ViewModelFactory(MainApplication.injection)
    })

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<DataItem>(EXTRA_USER)
        if (user != null){
            binding.tvSelectedUser.text = "${user.firstName} ${user.lastName}"
        }

            val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
            val name = sharedPreferences.getString("name", "Default Name")
            binding.tvName.text = name

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}