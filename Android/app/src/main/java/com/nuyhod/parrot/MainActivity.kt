package com.nuyhod.parrot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.nuyhod.parrot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val notification: NotificationListener = NotificationListener()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var tts: BaseTTS
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!permissionGranted()) {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.apply {
            mainViewModel = MainViewModel()
            lifecycleOwner = this@MainActivity
            viewmodel = mainViewModel
            textView = findViewById(R.id.message_text)
        }


    }

    private fun permissionGranted(): Boolean {
        val sets: Set<String> = NotificationManagerCompat.getEnabledListenerPackages(this)
        return sets != null && sets.contains(packageName)
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.endTTS()
    }
}
