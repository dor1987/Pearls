package a1door.pearls.Logic.Interfaces.Tools;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import a1door.pearls.R;
import a1door.pearls.View.Activities.MainActivity;

public class NotificationReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent repeating_intent = new Intent(context, MainActivity.class);
        long when = System.currentTimeMillis();

        String destenation = intent.getExtras().getString("dest");

        String morning = "0";
        String night = "0";
        String daily = "0";

        if(destenation.compareTo("morning")==0){
            morning="1";
        }
        if(destenation.compareTo("night")==0){
            night="1";
        }
        if(destenation.compareTo("daily")==0){
            daily="1";
        }


        repeating_intent.putExtra("morning",morning);
        repeating_intent.putExtra("night",night);
        repeating_intent.putExtra("daily",daily);



     //   Intent repeating_intent = new Intent(context, MainActivity.class);
       // loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, intent.getExtras().getString("channel"))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(intent.getExtras().getString("title"))
                .setContentText(intent.getExtras().getString("content"))
                .setSound(alarmSound)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setWhen(when)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(intent.getExtras().getInt("id"), mBuilder.build());
    }
}
