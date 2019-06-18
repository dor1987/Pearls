package a1door.pearls.View.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import a1door.pearls.Logic.Interfaces.Classes.Day;
import a1door.pearls.Logic.Interfaces.Classes.Profile;
import a1door.pearls.Logic.Interfaces.Interface.RefreshDataSetListener;
import a1door.pearls.Logic.Interfaces.Interface.getDayText;
import a1door.pearls.Logic.Interfaces.Interface.onCalanderCeremonyBtnsClickListener;
import a1door.pearls.Logic.Interfaces.Interface.onCeramonyViewListener;
import a1door.pearls.Logic.Interfaces.Interface.onDayClickedListener;
import a1door.pearls.Logic.Interfaces.Interface.onMonthChangeListener;
import a1door.pearls.Logic.Interfaces.Interface.onNewDailyTraining;
import a1door.pearls.Logic.Interfaces.Interface.onNewMorningCeramony;
import a1door.pearls.Logic.Interfaces.Interface.onNewNightCeramony;
import a1door.pearls.R;
import a1door.pearls.View.Fragments.CalendarFragment;
import a1door.pearls.View.Fragments.editInfoFragment.DailyTrainingFragment;
import a1door.pearls.View.Fragments.editInfoFragment.MorningCeremonyFragment;
import a1door.pearls.View.Fragments.editInfoFragment.NightCeremonyFragment;
import a1door.pearls.View.Fragments.viewInfoFragments.DailyTrainingShowFragment;
import a1door.pearls.View.Fragments.viewInfoFragments.MorningCeremoneyShowFragment;
import a1door.pearls.View.Fragments.viewInfoFragments.NightCeremoneyShowFragment;

public class MainActivity extends AppCompatActivity implements onCalanderCeremonyBtnsClickListener.CeramonyBtnsShowListener , onCeramonyViewListener.insideCeramonyBtnsShowListener , onMonthChangeListener.MonthChangeListenerListener ,onDayClickedListener.DayClickedListener, getDayText,RefreshDataSetListener ,onNewMorningCeramony.newMorningCeramony ,onNewNightCeramony.newNightCeramony,onNewDailyTraining.newDailyTraining,NavigationView.OnNavigationItemSelectedListener{

    DatabaseHelper myDatabase;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private int CurrentFragment;
    private String[] toolbarTitles;
    private ActionBarDrawerToggle toggle;
    private CalendarFragment calendarFragment;

    private MorningCeremoneyShowFragment morningCeremoneyShowFragment;
    private DailyTrainingShowFragment dailyTrainingShowFragment;
    private NightCeremoneyShowFragment nightCeremoneyShowFragment;

    private MorningCeremonyFragment moriningCeramonyFragment;
    private DailyTrainingFragment dailyTrainingFragment;
    private NightCeremonyFragment nightCeremonyFragment;

    private Fragment fragment;

    private ArrayList<Day> list;
    private Day currentDayclicked;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState)     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = new DatabaseHelper(this);

        profile = Profile.getInstance();

        calendarFragment = new CalendarFragment();
        morningCeremoneyShowFragment = new MorningCeremoneyShowFragment();
        dailyTrainingShowFragment = new DailyTrainingShowFragment();
        nightCeremoneyShowFragment = new NightCeremoneyShowFragment();

        moriningCeramonyFragment = new MorningCeremonyFragment();
        nightCeremonyFragment = new NightCeremonyFragment();
        dailyTrainingFragment = new DailyTrainingFragment();


/*
        myDatabase.insertDailyDateAndSentence(calendarFragment.getDate(),"eim ein ani li mi yahmi li");
        myDatabase.insertMorningCeramony(calendarFragment.getDate(),"morning1","morning2");
        myDatabase.insertNightCeramony(calendarFragment.getDate(),"nigh1","night2","night3");
        myDatabase.insertDailyTrainingCeramony(calendarFragment.getDate(),"dailyQ 1","dailyA 1");

        Date tempDate = new Date("Sat Aug 12 12:55:16 GMT+00:00 2018");
        myDatabase.insertDailyDateAndSentence(tempDate,"eim ein ani li mi yahmi li2");
        myDatabase.insertMorningCeramony(tempDate,"2morning1","2morning2");
        myDatabase.insertDailyTrainingCeramony(tempDate,"2dailyQ 1","2dailyA 1");

        Date tempDate2 = new Date("Sat Aug 15 12:55:16 GMT+00:00 2018");
        myDatabase.insertDailyDateAndSentence(tempDate2,"eim ein ani li mi yahmi li3");
        myDatabase.insertNightCeramony(tempDate2,"3nigh1","3night2","3night3");
        myDatabase.insertDailyTrainingCeramony(tempDate2,"3dailyQ 1","3dailyA 1");

        ArrayList<Day> list = myDatabase.readMonth(calendarFragment.getDate());




        for(int i = 0; list.size()>i;i++) {
            Log.e("Date ", list.get(i).getDate() + "");
            Log.e("Morning ans 1 ", list.get(i).getMorningAnswer1() + "");
            Log.e("Morning ans 2 ", list.get(i).getMorningAnswer2() + "");

            Log.e("Night ans1 ", list.get(i).getNightAnswer1() + "");
            Log.e("Night ans2 ", list.get(i).getNightAnswer2() + "");
            Log.e("Night ans3 ", list.get(i).getNightAnswer3() + "");

            Log.e("daily Ques ", list.get(i).getDailyQuestion() + "");
            Log.e("daily Ans ", list.get(i).getDailyAnswer() + "");
        }
*/
        calendarFragment.registerCeramonyBtnsShowListeners(this);
        calendarFragment.registerOnMonthChangeListener(this);
        calendarFragment.registerOnDayClickedListener(this);

        morningCeremoneyShowFragment.registerinsideCeramonyBtnsShowListeners(this);
        dailyTrainingShowFragment.registerinsideCeramonyBtnsShowListeners(this);
        nightCeremoneyShowFragment.registerinsideCeramonyBtnsShowListeners(this);

        moriningCeramonyFragment.registerinsideCeramonyBtnsShowListeners(this);
        moriningCeramonyFragment.registerNewMorningCeramonyisteners(this);
        nightCeremonyFragment.registerinsideCeramonyBtnsShowListeners(this);
        nightCeremonyFragment.registerNewNightCeramonyisteners(this);
        dailyTrainingFragment.registerinsideCeramonyBtnsShowListeners(this);
        dailyTrainingFragment.registerNewDailyTrainingisteners(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR}, 0);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userNickName)).setText(profile.getNickName());
        ((TextView) navigationView.getHeaderView(0).findViewById(R.id.userName)).setText(profile.getName());



        navigationView.setNavigationItemSelectedListener(this);

        toolbarTitles = getResources().getStringArray(R.array.nav_titles);



        //CurrentFragment = 0;

        Intent  intent =  getIntent();

        String morning = intent.getExtras().getString("morning");
        String night = intent.getExtras().getString("night");
        String daily = intent.getExtras().getString("daily");


        if(intent.getExtras().getString("dest")==null) {
            if (morning.compareTo("1") == 0)
                CurrentFragment = 4;

            else if (night.compareTo("1") == 0)
                CurrentFragment = 5;

            else if (daily.compareTo("1") == 0)
                CurrentFragment = 6;
        }
        else{
            CurrentFragment = 0;

        }
        SetCurrentFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        int lastTimeStarted = settings.getInt("last_time_started", -1);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        if (today != lastTimeStarted) {
            updateDailyQuate();

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("last_time_started", today);
            editor.commit();
        }
    }


    private void updateDailyQuate() {
        myDatabase.insertDailyDateAndSentence(calendarFragment.getDate(),"אם אין אני לי מי לי");
    }


    private void SetCurrentFragment()
    {
        SetItemFocus();
        SetToolBarTitle();
        FragmentTransaction fragmentTransaction;
        switch (CurrentFragment)
        {
            case 0:
                fragment = calendarFragment;
                break;
            case 1:
                fragment = morningCeremoneyShowFragment;
                break;
            case 2:
                fragment = nightCeremoneyShowFragment;
                break;
            case 3:
                fragment = dailyTrainingShowFragment;
                break;
            case 4:
                fragment = moriningCeramonyFragment;
                break;
            case 5:
                fragment = nightCeremonyFragment;
                break;
            case 6:
                fragment = dailyTrainingFragment;
                break;

            default:
                fragment = calendarFragment;

        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
        closeKeyBoard();
    }


    private void SetItemFocus()
    {/*
        if (navigationView.getMenu().getItem(CurrentFragment).isChecked() == false)
        {
            navigationView.getMenu().getItem(CurrentFragment).setChecked(true);
        }
*/
    }
    private void SetToolBarTitle()
    {
        ((TextView) findViewById(R.id.toolbarTitleText)).setText(this.toolbarTitles[CurrentFragment]);
    }

    @Override
    public void onBackPressed()
    {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();
       // if (id == R.id.addToolBarButton)
        {
           // showDialog();
        }
        return super.onOptionsItemSelected(item);
    }







    public void showDialog()
    {
/*
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
*/
    }

    public void RefreshDataSet()
    {
        ((RefreshDataSetListener) fragment).RefreshDataSet();
    }


    @Override
    public void onCeramonyBtnsClick(int whereToGo) {
        int fragmentNumber = whereToGo;

        if (fragmentNumber != CurrentFragment)
        {
            CurrentFragment = fragmentNumber;
            SetCurrentFragment();
        }
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public ArrayList<Day> onMonthViewChanged(Date currentDate) {
        //list.clear();
        list = myDatabase.readMonth(currentDate);
        return list;
    }

/*
    public void updateMorningCeramonyText(){
        morningCeremoneyShowFragment.setQuestionText(currentDayclicked.getMorningAnswer1(),currentDayclicked.getMorningAnswer2());
    }

    public void updateNightCeramonyText(){
        nightCeremoneyShowFragment.setQuestionText(currentDayclicked.getNightAnswer1(),currentDayclicked.getNightAnswer2(),currentDayclicked.getNightAnswer3());
    }

    public void updateDailyTrainingText(){
        dailyTrainingShowFragment.setQuestionText(currentDayclicked.getDailyQuestion());
        dailyTrainingShowFragment.setAnswerText(currentDayclicked.getDailyAnswer());
    }
*/

/*
    @Override
    public void onDayClicked(Day day) {
        if(day.getMorningAnswer1()!=null) {
            morningCeremoneyShowFragment.setQuestionText(day.getMorningAnswer1(),day.getMorningAnswer2());
        }

        if(day.getNightAnswer1()!=null) {
            nightCeremoneyShowFragment.setQuestionText(day.getNightAnswer1(),day.getNightAnswer2(),day.getNightAnswer3());
        }

        if(day.getDailyAnswer()!=null) {
            dailyTrainingShowFragment.setQuestionText(day.getDailyQuestion());
            dailyTrainingShowFragment.setAnswerText(day.getDailyAnswer());
        }

    }
*/

    @Override
    public void onDayClicked(Day day) {
        currentDayclicked = day;
    }

    @Override
    public Day getDay() {
        return currentDayclicked;
    }

    @Override
    public void updateNewMorningCeramony(String text1, String text2) {
      //  myDatabase.insertMorningCeramony(calendarFragment.getDate(),text1,text2);
        myDatabase.insertMorningCeramony(calendarFragment.getDate(),text1,text2);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if(id == R.id.navProfileEdit){

                Intent intent = new Intent(this, UserProfileDefenitionActivity.class);
                intent.putExtra("edit", "edit");
                startActivity(intent);

            }
            else if(id == R.id.navSettings){

            }
            else if(id == R.id.navExit){
                this.finishAffinity();
            }


            drawer.closeDrawer(GravityCompat.START);

            return true;

    }

    @Override
    public void updateNewDailyTraining(String text1, String text2) {
        myDatabase.insertDailyTrainingCeramony(calendarFragment.getDate(),text1,text2);

    }

    @Override
    public void updateNewNightCeramony(String text1, String text2, String text3) {
        myDatabase.insertNightCeramony(calendarFragment.getDate(),text1,text2,text3);

    }

    private void closeKeyBoard(){
        View view  = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

}
