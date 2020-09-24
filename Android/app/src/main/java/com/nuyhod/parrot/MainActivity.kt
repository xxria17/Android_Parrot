package com.nuyhod.parrot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuyhod.parrot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val notification: NotificationListener = NotificationListener()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var tts: BaseTTS
    private lateinit var stt: BaseSTT
    private lateinit var textView: TextView
    private lateinit var sendBtn: View
    private lateinit var msgModel: MsgModel

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
            sendBtn = findViewById(R.id.send_button)
        }

        if (intent.hasExtra("packageName")) {
            val sender = intent.getStringExtra("sender")
            val text = intent.getStringExtra("text")
            val pckName = intent.getStringExtra("packageName")
            textView.text = "${sender} 님이 메세지를 보냈습니다. \n {${text}"
            mainViewModel.speakOut(this.applicationContext, pckName, sender, text)
        }


//        sendBtn.setOnClickListener {
//            mainViewModel.listen(packageName, this)
//        }
    }

    private fun permissionGranted(): Boolean {
        val sets: Set<String> = NotificationManagerCompat.getEnabledListenerPackages(this)
        return sets != null && sets.contains(packageName)
    }

}
