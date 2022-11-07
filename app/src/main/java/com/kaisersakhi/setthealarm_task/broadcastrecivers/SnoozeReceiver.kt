package com.kaisersakhi.setthealarm_task.broadcastrecivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kaisersakhi.setthealarm_task.utils.AlarmNotification
import com.kaisersakhi.setthealarm_task.utils.AppAlarm

class SnoozeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            AlarmNotification.cancelNotification()
            AppAlarm.setAlarm(it, true)
        }
    }
}