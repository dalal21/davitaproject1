package com.example.macbookpro.davitaproject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;


public class AlarmReceiver extends BroadcastReceiver {
    AlarmManager am;
    static String pill="";
    static String patient="";

    @Override
    public void onReceive(final Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "test");
        wl.acquire();

        Toast.makeText(context, "take your pills", Toast.LENGTH_LONG).show();
        showNotification(context);
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        wl.release();

    }

    private static void showNotification(Context context) {

        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.clock);
        mBuilder.setContentTitle("Reminde me");

        mBuilder.setContentText(pill+" for "+patient+" Please ..");
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        Uri sound = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
        mBuilder.setSound(sound);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.clock);
        mBuilder.setLargeIcon(icon);
        mBuilder.setAutoCancel(true);

        Notification n = mBuilder.build();

        NotificationManager mNotificationManager =(NotificationManager)
        context.getSystemService(Context.NOTIFICATION_SERVICE);


            NotificationID.getID();
            mNotificationManager.notify(NotificationID.getID(), n);



    }



    public static class NotificationID {
        private final static AtomicInteger c = new AtomicInteger(0);
        public static int getID() {
            return c.incrementAndGet();
        }
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

    public void setinfo(String pi,String pa){
        pill=pi;
        patient=pa;
    }
    /*public void delala(Context context){
        am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, _id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.cancel(pendingIntent);

    }*/


}
