package com.nuyhod.parrot

import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.lifecycle.MutableLiveData

class NotificationListener : NotificationListenerService() {

    private lateinit var msgModel: MsgModel
    val mutableData = MutableLiveData<MsgModel>()

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val extras: Bundle? = sbn?.notification?.extras
        val sender: String? = extras?.getString(Notification.EXTRA_TITLE)
        val text: CharSequence? = extras?.getCharSequence(Notification.EXTRA_TEXT)
        val packageName: String? = sbn?.packageName


        if (sender != null && text != null && packageName != null) {
            Log.d("Notification Listener", "sender :: $sender \n text :: $text \n packageName :: $packageName")
//            msgModel = MsgModel(sender, text, packageName)
//            mutableData.value = msgModel
            val notifyIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("sender", sender)
                putExtra("text", text)
                putExtra("packageName" , packageName)
            }
            startActivity(notifyIntent)
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)

    }
}