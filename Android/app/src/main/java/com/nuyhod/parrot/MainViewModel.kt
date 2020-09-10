package com.nuyhod.parrot

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    private lateinit var tts: BaseTTS

    private fun filterPck(msgModel: MsgModel): String {
        if (msgModel.pckName?.equals(R.string.kakao_talk)!! || msgModel.pckName?.equals(R.string.instagram)!! || msgModel.pckName?.equals(
                R.string.message
            )!! ||
            msgModel.pckName?.equals(R.string.face_book)!!
        ) {
            return "${msgModel.sender} 님이 메세지를 보냈습니다. \n ${msgModel.text}"
        } else {
            return "${msgModel.text}"
        }
    }

    public fun speakOut(context: Context, msgModel: MsgModel) {
        Log.d("MAIN VIEWMODEL", "실행됨")
        val text: String = filterPck(msgModel)
        tts.setTTS(context)
        tts.sayTTS(context, text)
    }

}