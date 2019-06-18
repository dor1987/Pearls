package a1door.pearls.View.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import a1door.pearls.Logic.Interfaces.Classes.Profile;
import a1door.pearls.Logic.Interfaces.Tools.NotificationManager;
import a1door.pearls.R;
import a1door.pearls.View.Fragments.TimePickerFragment;

public class UserProfileDefenitionActivity extends AppCompatActivity{
    Profile profile;
    private NotificationManager notificationManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        this.notificationManager = new NotificationManager();
        profile = Profile.getInstance();
        profile.setSharedPreferences(getSharedPreferences("pearlsSharedPreferences",Context.MODE_PRIVATE));
        profile.initFromSharedPreferences();


                if (!profile.isFirstLogIn()) {
                    Intent tempIntent = getIntent();
                    Bundle extras = tempIntent.getExtras();
                    if (extras == null) {
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("dest", "normal");
                        startActivity(intent);
                    }
                    else{
                        if (!extras.containsKey("edit")) {
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtra("dest", "normal");
                            startActivity(intent);
                        }

                        EditText editTextUserName = findViewById(R.id.getUserName);
                        editTextUserName.setText(profile.getName());

                        EditText editTextUserNickName = findViewById(R.id.getUserNickName);
                        editTextUserNickName.setText(profile.getNickName());

                    }
                }


    }

    public void showTimePickerDialogMorning(View v) {
        DialogFragment newFragment = new TimePickerFragment("morning");
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showTimePickerDialogNight(View v) {
        DialogFragment newFragment = new TimePickerFragment("night");
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showTimePickerDialogDaily(View v) {
        DialogFragment newFragment = new TimePickerFragment("daily");
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


    public void saveName() {
        EditText editText = findViewById(R.id.getUserName);
        profile.setName(editText.getText().toString());
        closeKeyBoard();
    }

    public void saveNickName() {
        EditText editText = findViewById(R.id.getUserNickName);
        profile.setNickName(editText.getText().toString());
        closeKeyBoard();

    }


    public void clearNameBox(View v) {
        EditText editText = findViewById(R.id.getUserName);
        editText.setText("");
    }

    public void clearNickNameBox(View v) {
        EditText editText = findViewById(R.id.getUserNickName);
        editText.setText("");
    }

    public void moveToNextActivity(View v) {
        if(profile.isFirstLogIn()) {
            AddNotification("morning", "Good Morning", "Time For Morning Ceremony", this, profile.getMorningNotificationTime());
            AddNotification("night", "Hey Beautiful", "Time For Night Ceremony", this, profile.getNightNotificationTime());
            AddNotification("daily", "Hey :D", "how about some self love training?", this, profile.getDailyTrainingNotificationTime());
        }

        else{
            UpdateNotification("morning",1,"Good Morning", "Time For Morning Ceremony",profile.getMorningNotificationTime(),this);
            UpdateNotification("night", 2,"Hey Beautiful", "Time For Night Ceremony", profile.getNightNotificationTime(),this);
            UpdateNotification("daily", 3,"Hey :D", "how about some self love training?", profile.getDailyTrainingNotificationTime(), this);
        }

        saveName();
        saveNickName();

        saveToSharedPreferences();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dest", "normal");
        startActivity(intent);
    }

    private int AddNotification(String dest,String title, String content, Context context, Calendar calendar)
    {

            int pushId = notificationManager.GeneratePushId();
            notificationManager.AddNotification(pushId,dest, title, content, context, calendar);

            return pushId;
    }

    private int UpdateNotification(String dest,int pushId, String title, String content, Calendar calendar, Context context)
    {
        return notificationManager.UpdateNotification(dest,pushId, title, content, calendar, context);
    }

    private void closeKeyBoard(){
        View view  = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    public void saveToSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("pearlsSharedPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username",profile.getName());
        editor.putString("usernickname",profile.getNickName());
        editor.putLong("morningnotification",profile.getDailyTrainingNotificationTime().getTimeInMillis());
        editor.putLong("nightnotification",profile.getNightNotificationTime().getTimeInMillis());
        editor.putLong("dailytrainingnotification",profile.getDailyTrainingNotificationTime().getTimeInMillis());
        editor.putBoolean("isfirstlogin",false);
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }


}
