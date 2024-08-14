package com.zycom.suitmediasubmission.ui.screen.main
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zycom.suitmediasubmission.databinding.ActivityMainBinding
import com.zycom.suitmediasubmission.ui.screen.second.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnCheck.setOnClickListener {
            val sentence = binding.inputSentence.text.toString().replace("\\s".toRegex(), "")
            if (isPalindrome(sentence)) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

            val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("name",binding.inputName.text.toString())
            editor.apply()
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val reversedText = text.reversed()
        return text.equals(reversedText, ignoreCase = true)
    }
}