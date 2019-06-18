package a1door.pearls.View.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import a1door.pearls.Logic.Interfaces.Classes.Profile;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    Profile profile;
    String whichNotificationToSet;

    @SuppressLint("ValidFragment")
    public TimePickerFragment(String whichNotificationToSet) {
        super();
        this.whichNotificationToSet = whichNotificationToSet;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        profile = Profile.getInstance();

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        final Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);


        if (whichNotificationToSet.compareTo("morning")==0) {
            profile.setMorningNotificationTime(c.getTimeInMillis());
        }

        else if(whichNotificationToSet.compareTo("night")==0){
            profile.setNightNotificationTime(c.getTimeInMillis());
        }
        else if(whichNotificationToSet.compareTo("daily")==0){
            profile.setDailyTrainingNotificationTime(c.getTimeInMillis());
        }

    }
}
