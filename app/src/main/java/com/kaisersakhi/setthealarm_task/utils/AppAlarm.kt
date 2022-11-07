package com.kaisersakhi.setthealarm_task.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.kaisersakhi.setthealarm_task.broadcastrecivers.AlarmReceiver
import java.util.concurrent.TimeUnit

const val ALARM_INTENT_KEY = "date"
object AppAlarm {
    private var alarmManager: AlarmManager? = null
    private var numberOfAlarms = 0
    private var bid = 0 //will signify broadcast id
    private val TAG = this::class.simpleName
    private const val timeGap = 5 * 60 * 1000 // 5 minutes

    init {
        Log.d(this::class.simpleName, ": object created")
    }

    fun setAlarm(myApp: Context, isSnoozed: Boolean = false) {
        ++this.numberOfAlarms
        this.bid++
        if (this.alarmManager == null)
            alarmManager = ContextCompat.getSystemService(
                myApp,
                AlarmManager::class.java
            ) as AlarmManager

        val intent = Intent(myApp, AlarmReceiver::class.java).apply {
            putExtra(ALARM_INTENT_KEY, System.currentTimeMillis())
        }

        val pendingIntent = PendingIntent.getBroadcast(
            myApp,
            this.bid,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val alrmTime : Long = if (!isSnoozed)
            System.currentTimeMillis() + (timeGap * numberOfAlarms)
        else
            System.currentTimeMillis() + (timeGap)

        alarmManager?.setExact(
            AlarmManager.RTC_WAKEUP,
            alrmTime,
            pendingIntent
        )


        val minutes = TimeUnit.MILLISECONDS.toMinutes(
            if (isSnoozed) timeGap.toLong() else timeGap * numberOfAlarms.toLong()
        )


        Toast.makeText(
            myApp,
            "Alarm Will Go Off in $minutes minutes.",
            Toast.LENGTH_LONG
        ).show()
        Log.d(TAG, "setAlarm: $numberOfAlarms")

    }

    fun resetAlarmCounter() {
        // will reset the alarm counter once app goes to foreground
        this.numberOfAlarms = 0
    }
}