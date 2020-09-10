package com.nuyhod.parrot

import android.app.Notification
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NotificationListener : NotificationListenerService() {

    private lateinit var msgModel: MsgModel
     val mutableData = MutableLiveData<MsgModel>()
//    val data = LiveData<MsgModel>()

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        val extras: Bundle? = sbn?.notification?.extras
        val sender: String? = extras?.getString(Notification.EXTRA_TITLE)
        val text: CharSequence? = extras?.getCharSequence(Notification.EXTRA_TEXT)
        val packageName: String? = sbn?.packageName


        if (sender != null && text != null && packageName != null) {
            Log.d("Notification Listener", "sender :: $sender \n text :: $text \n packageName :: $packageName")
            msgModel = MsgModel(sender, text, packageName)
            mutableData.value = msgModel
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)

    }
}