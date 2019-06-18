package a1door.pearls.Logic.Interfaces.Interface;

import a1door.pearls.Logic.Interfaces.Classes.Day;

public interface onDayClickedListener {

    void registerOnDayClickedListener(DayClickedListener listener);
    void deleteOnDayClickedListener(DayClickedListener listener);

    interface DayClickedListener{
      void onDayClicked(Day day);
    }
}
