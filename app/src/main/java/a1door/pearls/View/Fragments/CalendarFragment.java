package a1door.pearls.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import a1door.pearls.Logic.Interfaces.Classes.Day;
import a1door.pearls.Logic.Interfaces.Interface.RefreshDataSetListener;
import a1door.pearls.Logic.Interfaces.Interface.onCalanderCeremonyBtnsClickListener;
import a1door.pearls.Logic.Interfaces.Interface.onDayClickedListener;
import a1door.pearls.Logic.Interfaces.Interface.onMonthChangeListener;
import a1door.pearls.R;
import a1door.pearls.View.Activities.MainActivity;

public class CalendarFragment extends android.support.v4.app.Fragment implements onCalanderCeremonyBtnsClickListener , onMonthChangeListener ,onDayClickedListener ,RefreshDataSetListener
{
    private final String DATE_PATTERN = "MMMM yyyy";
    private CompactCalendarView calendarView;
    private RecyclerView calendarItemList;
    private TextView dateText;
    private Date currentDate;
    private CeramonyBtnsShowListener mOnCalanderCeremonyBtnsClickListener;
    private MonthChangeListenerListener monthChangeListenerListener;
    private DayClickedListener dayClickedListener;
    private ArrayList<Day> list;
    private Button morningCeramonyShowBtn;
    private Button nightCeramonyShowBtn;
    private Button dailyTrainingShowBtn;
    private TextView dailyQuote;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);


        dateText = view.findViewById(R.id.dateText);
        dailyQuote = view.findViewById(R.id.dailyQuote);

        /*
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        calendarItemList.setLayoutManager(mLayoutManager);
        calendarItemList.setItemAnimator(new DefaultItemAnimator());
        calendarItemList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        */

        morningCeramonyShowBtn = (Button) view.findViewById(R.id.btn1);
        nightCeramonyShowBtn = (Button) view.findViewById(R.id.btn2);
        dailyTrainingShowBtn = (Button) view.findViewById(R.id.btn3);
        morningCeramonyShowBtn.setVisibility(View.GONE);
        nightCeramonyShowBtn.setVisibility(View.GONE);
        dailyTrainingShowBtn.setVisibility(View.GONE);

        initOnClickBtnListeners(view);


        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        if (currentDate == null)
        {
           // currentDate = calendarView.getFirstDayOfCurrentMonth();
            currentDate = new Date();
        }
        else
        {
            calendarView.setCurrentDate(currentDate);
        }


        if(monthChangeListenerListener!=null)
            list = monthChangeListenerListener.onMonthViewChanged(currentDate);

        //RefreshCalendarEvents();

        RefreshDataSet();

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener()
        {
            @Override
            public void onDayClick(Date dateClicked)
            {
                currentDate = dateClicked;
                RefreshDataSet();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth)
            {
                currentDate = firstDayOfNewMonth;

                if(monthChangeListenerListener!=null)
                    list = monthChangeListenerListener.onMonthViewChanged(currentDate);

                RefreshDataSet();
            }
        });

        return view;
    }

    private void initOnClickBtnListeners(View view) {
        //Morning Ceremony Button
        View.OnClickListener mMorningCeramonyShowButtonClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (mOnCalanderCeremonyBtnsClickListener != null)
                    mOnCalanderCeremonyBtnsClickListener.onCeramonyBtnsClick(1);

            }
        };

        //Button morningCeramonyShowBtn = (Button) view.findViewById(R.id.btn1);
        morningCeramonyShowBtn.setOnClickListener(mMorningCeramonyShowButtonClickListener);


        //Night Ceremony Button
        View.OnClickListener mNightCeramonyShowButtonClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (mOnCalanderCeremonyBtnsClickListener != null)
                    mOnCalanderCeremonyBtnsClickListener.onCeramonyBtnsClick(2);
            }
        };

        //Button nightCeramonyShowBtn = (Button) view.findViewById(R.id.btn2);
        nightCeramonyShowBtn.setOnClickListener(mNightCeramonyShowButtonClickListener);

        //Daily training  Button
        View.OnClickListener mDailyTrainingShowButtonClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (mOnCalanderCeremonyBtnsClickListener != null)
                    mOnCalanderCeremonyBtnsClickListener.onCeramonyBtnsClick(3);
            }
        };

        //Button dailyTrainingShowBtn = (Button) view.findViewById(R.id.btn3);
        dailyTrainingShowBtn.setOnClickListener(mDailyTrainingShowButtonClickListener);


    }

    private void RefreshCalendarEvents()
    {
        ArrayList<Event> events = new ArrayList<Event>();


        for(int i = 0; i<list.size();i++){
            Calendar calendar = Calendar.getInstance();
            Day tempDay = list.get(i);
            Date tempDate = tempDay.getDate();
            calendar.setTime(tempDate);

            if(list.get(i).getMorningAnswer1()!=null) {
                Event ev = new Event(Color.GREEN, calendar.getTimeInMillis());
                events.add(ev);
            }

            if(list.get(i).getNightAnswer1()!=null) {
                Event ev = new Event(Color.BLUE, calendar.getTimeInMillis());
                events.add(ev);
            }

            if(list.get(i).getDailyAnswer()!=null) {
                Event ev = new Event(Color.YELLOW, calendar.getTimeInMillis());
                events.add(ev);
            }
        }

        calendarView.addEvents(events);
    }

    public void RefreshDataSet()
    {
        UpdateDateText(currentDate);

        RefreshCalendarEvents();
        RefreshDailyQuate(currentDate);
        RefreshBtns(currentDate);
        RefreshShowFragment(currentDate);
    }

    private void RefreshDailyQuate(Date currentDate) {
        for(int i = 0; i<list.size();i++) {
            if (list.get(i).getDate().getTime() == currentDate.getTime()) {
                if (list.get(i).getDailySentence() != null) {
                    dailyQuote.setText(list.get(i).getDailySentence());

                }
            }
        }
    }

    private void RefreshShowFragment(Date currentDate) {
        for(int i = 0; i<list.size();i++) {
            if(list.get(i).getDate().getTime()==currentDate.getTime()) {
                if (dayClickedListener != null)
                    dayClickedListener.onDayClicked(list.get(i));
                break;
            }
        }
    }

    private void RefreshBtns(Date currentDate) {
        for(int i = 0; i<list.size();i++){
            if(list.get(i).getDate().getTime()==currentDate.getTime()){
                if(list.get(i).getMorningAnswer1()!=null) {
                    morningCeramonyShowBtn.setVisibility(View.VISIBLE);
                }
                else{
                    morningCeramonyShowBtn.setVisibility(View.GONE);
                }

                if(list.get(i).getNightAnswer1()!=null) {
                    nightCeramonyShowBtn.setVisibility(View.VISIBLE);
                }
                else{
                    nightCeramonyShowBtn.setVisibility(View.GONE);
                }
                if(list.get(i).getDailyAnswer()!=null) {
                    dailyTrainingShowBtn.setVisibility(View.VISIBLE);
                }
                else{
                    dailyTrainingShowBtn.setVisibility(View.GONE);
                }
                break;
            }
            morningCeramonyShowBtn.setVisibility(View.GONE);
            nightCeramonyShowBtn.setVisibility(View.GONE);
            dailyTrainingShowBtn.setVisibility(View.GONE);
        }

    }


    private void UpdateDateText(Date date)
    {
        SimpleDateFormat ft = new SimpleDateFormat(DATE_PATTERN);
        dateText.setText(ft.format(date));
    }

    @Override
    public void registerCeramonyBtnsShowListeners(CeramonyBtnsShowListener listener) {
        mOnCalanderCeremonyBtnsClickListener = listener;
    }

    @Override
    public void deleteCeramonyBtnsShowListeners(CeramonyBtnsShowListener listener) {
        mOnCalanderCeremonyBtnsClickListener = null;
    }

    public Date getDate(){

        return new Date();
    }

    @Override
    public void registerOnMonthChangeListener(MonthChangeListenerListener listener) {
        monthChangeListenerListener = listener;
    }

    @Override
    public void deleteOnMonthChangeListener(MonthChangeListenerListener listener) {
        monthChangeListenerListener = null;
    }

    @Override
    public void registerOnDayClickedListener(DayClickedListener listener) {
        dayClickedListener = listener;
    }

    @Override
    public void deleteOnDayClickedListener(DayClickedListener listener) {
        dayClickedListener = null;
    }
}
