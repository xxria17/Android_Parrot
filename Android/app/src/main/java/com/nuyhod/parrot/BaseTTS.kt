package com.nuyhod.parrot

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import java.util.*

class BaseTTS {
    private var tts: TextToSpeech? = null

    public fun setTTS(context: Context) {
        tts = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it != TextToSpeech.ERROR) {
                tts!!.language = Locale.KOREAN
            }
        })
    }

    public fun sayTTS(context: Context, text: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts!!.speak(text, TextToSpeech.QUEUE_ADD, null, null)
        } else {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    public fun endTTS() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
            tts = null
        }
    }
}