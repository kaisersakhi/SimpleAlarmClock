package com.kaisersakhi.setthealarm_task.broadcastrecivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kaisersakhi.setthealarm_task.utils.AlarmNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            AlarmNotification.showNotification(it)
            CoroutineScope(Dispatchers.IO).launch{
                delay(40*1000L)
                AlarmNotification.cancelNotification()
            }
        }
    }
}