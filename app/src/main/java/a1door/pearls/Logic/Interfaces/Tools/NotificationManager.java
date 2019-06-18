package a1door.pearls.Logic.Interfaces.Tools;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;



public class NotificationManager
{
    private int pushId;
    private final String CHANNELIS = "Pearls";

    public NotificationManager(int pushId)
    {
        this.pushId = pushId;
    }

    public NotificationManager()
    {
        this.pushId = 1;
    }

    public void AddNotification(int pushId,String dest, String title, String content, Context context, Calendar calendar)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
       /*
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(calendar.getTimeInMillis());
        calendar1.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
        calendar1.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
        */
        Intent myIntent = new Intent(context, NotificationReceiver.class);

        myIntent.putExtra("title", title);
        myIntent.putExtra("content", content);
        myIntent.putExtra("channel", CHANNELIS);
        myIntent.putExtra("id", pushId);
        myIntent.putExtra("dest", dest);
        System.out.println("Add Push " + pushId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pushId, myIntent, 0);

       // alarmManager.set(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

    }

    public void setPushId(int pushId)
    {
        this.pushId = pushId;
    }

    public void CancelNotification(int pushId, Context context)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        System.out.println("Delete Push " + pushId);
        Intent myIntent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pushId, myIntent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public int UpdateNotification(String dest,int pushId, String title, String content, Calendar calendar, Context context)
    {
        CancelNotification(pushId, context);
        //int newPushId = getPushId();
        AddNotification(pushId,dest, title, content, context, calendar);
        //return newPushId;
        return pushId;
    }

    public int getPushId()
    {
        return pushId;
    }

    public int GeneratePushId()
    {
        this.pushId++;
        return pushId - 1;
    }
}
