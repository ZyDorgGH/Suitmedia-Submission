package com.zycom.suitmediasubmission.ui.screen.third

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zycom.suitmediasubmission.ui.MainApplication
import com.zycom.suitmediasubmission.data.factory.ViewModelFactory
import com.zycom.suitmediasubmission.databinding.ActivityThirdBinding
import com.zycom.suitmediasubmission.ui.adapter.LoadingStateAdapter
import com.zycom.suitmediasubmission.ui.adapter.UserListAdapter
import com.zycom.suitmediasubmission.ui.screen.second.SecondActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val viewModel: ThirdViewModel by viewModels(factoryProducer = {
        ViewModelFactory(MainApplication.injection)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.apply {
            with(rvContact) {
                layoutManager = LinearLayoutManager(this@ThirdActivity)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
        getData()

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {

        val adapter = UserListAdapter(onClick = { user, optionCompat ->
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(SecondActivity.EXTRA_USER, user)
            startActivity(intent, optionCompat.toBundle())
        })

        binding.rvContact.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )
        viewModel.story().observe(this) {
            adapter.submitData(lifecycle, it)
        }
        adapter.notifyDataSetChanged()
    }

}