package com.kaisersakhi.setthealarm_task.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.VibrationEffect
import android.util.Log
import androidx.core.app.NotificationCompat
import com.kaisersakhi.setthealarm_task.R
import com.kaisersakhi.setthealarm_task.broadcastrecivers.SnoozeReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

object AlarmNotification {
    private const val NOTIFICATION_CHANNEL_ID = "my_alarm"
    private const val NOTIFICATION_ID = 24
    private var notificationManager: NotificationManager? = null
    fun showNotification(context: Context) {
        val intent = Intent(context, SnoozeReceiver::class.java)

        val snoozePendingIntent = PendingIntent
            .getBroadcast(context, 10, intent, PendingIntent.FLAG_IMMUTABLE)

        val notificationChannel =
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID, "Alarm", NotificationManager.IMPORTANCE_HIGH
            )
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern = longArrayOf(0, 1000, 1000, 1000, 1000, 1000)
        notificationChannel.setSound(
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE),
            null
        )



        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager?.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_clock)
            .setContentTitle("Wakie Wakie...")
            .addAction(R.drawable.ic_snooze, "Snooze", snoozePendingIntent)
            .build()

        notificationManager?.notify(this.NOTIFICATION_ID, notification)

        CoroutineScope(Dispatchers.IO).launch {
            VibrationEffect.createWaveform(
                longArrayOf(0, 1000, 1000, 1000),
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        }
    }

    fun cancelNotification() {
        this.notificationManager?.cancel(this.NOTIFICATION_ID)
        Log.d(this::class.simpleName, "cancelNotification: cancelled")
    }
}