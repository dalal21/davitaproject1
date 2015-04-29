package com.example.macbookpro.davitaproject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
public class AlarmReceiver extends BroadcastReceiver {
    AlarmManager am;

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "test");

        wl.acquire();

        Toast.makeText(context, "take your pills", Toast.LENGTH_LONG).show();
        Log.i("test", "ALARM!!!");
        showNotification(context);
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        //Release the lock

        wl.release();

    }

    private void showNotification(Context context) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setContentTitle("My notification")
                        .setContentText("why dont ya take them old man");
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }


    public void setOnetimeTimer(Context context, long when) {
        am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent appIntent = PendingIntent.getBroadcast(context, _id, intent,PendingIntent.FLAG_ONE_SHOT);
        //PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //am.set(AlarmManager.RTC_WAKEUP, when, pi);
        am.set(AlarmManager.RTC_WAKEUP, when, appIntent);
    }


}
