package a1door.pearls.Logic.Interfaces.Interface;

import java.util.ArrayList;
import java.util.Date;

import a1door.pearls.Logic.Interfaces.Classes.Day;

public interface onMonthChangeListener {

    void registerOnMonthChangeListener(MonthChangeListenerListener listener);
    void deleteOnMonthChangeListener(MonthChangeListenerListener listener);


    interface MonthChangeListenerListener{
        ArrayList<Day> onMonthViewChanged(Date currentDate);
    }
}
