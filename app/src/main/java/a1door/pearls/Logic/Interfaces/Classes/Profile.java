package a1door.pearls.Logic.Interfaces.Classes;

import android.content.SharedPreferences;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

import a1door.pearls.Logic.Interfaces.Tools.NotificationManager;

public class Profile {
    private static Profile instance;


    private String name;
    private String nickName;
    private Calendar morningNotificationTime= Calendar.getInstance();
    private Calendar nightNotificationTime= Calendar.getInstance();
    private Calendar dailyTrainingNotificationTime= Calendar.getInstance();
    private boolean firstLogIn;
    private SharedPreferences sharedPreferences;


    private Profile() {
    }

    private Profile(String name, String nickName, Calendar morningNotificationTime, Calendar nightNotificationTime, Calendar dailyTrainingNotificationTime, Boolean firstLogIn) {
        this.name = name;
        this.nickName = nickName;
        this.morningNotificationTime = morningNotificationTime;
        this.nightNotificationTime = nightNotificationTime;
        this.dailyTrainingNotificationTime = dailyTrainingNotificationTime;
        this.firstLogIn = firstLogIn;
    }

    public static Profile getInstance()
    {
        if (instance == null) {
            instance = new Profile();
        }
        return(instance);
    }


    public void initFromSharedPreferences(){
          name = sharedPreferences.getString("username","לא הוכנס שם");
          nickName= sharedPreferences.getString("usernickname","לא הוכנס כינוי");
          morningNotificationTime.setTimeInMillis(sharedPreferences.getLong("morningnotification",0));
          nightNotificationTime.setTimeInMillis(sharedPreferences.getLong("nightnotification",0));
          dailyTrainingNotificationTime.setTimeInMillis(sharedPreferences.getLong("dailytrainingnotification",0));
          firstLogIn = sharedPreferences.getBoolean("isfirstlogin",true);
    }

    public boolean isFirstLogIn() {
        return firstLogIn;
    }

    public void setFirstLogIn(boolean firstLogIn) {
        this.firstLogIn = firstLogIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Calendar getMorningNotificationTime() {
        return morningNotificationTime;
    }

    public void setMorningNotificationTime(Long morningNotificationTime) {
        this.morningNotificationTime.setTimeInMillis(morningNotificationTime);
    }

    public Calendar getNightNotificationTime() {
        return nightNotificationTime;
    }

    public void setNightNotificationTime(Long nightNotificationTime) {
        this.nightNotificationTime.setTimeInMillis(nightNotificationTime);
    }

    public Calendar getDailyTrainingNotificationTime() {
        return dailyTrainingNotificationTime;
    }

    public void setDailyTrainingNotificationTime(Long dailyTrainingNotificationTime) {
        this.dailyTrainingNotificationTime.setTimeInMillis(dailyTrainingNotificationTime);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
