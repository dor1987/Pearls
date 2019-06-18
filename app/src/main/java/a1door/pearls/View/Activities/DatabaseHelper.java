package a1door.pearls.View.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import a1door.pearls.Logic.Interfaces.Classes.Day;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final String DATABASE_NAME = "Ceremony.db";
    public static final String TABLE_NAME = "ceremony_table";

    public static final String DATE_COL = "DATE";

    public static final String SENTENCE_COL = "SENTENCE";

    public static final String MORNING_ANSWER1_COL = "MORNING_ANSWER1";
    public static final String MORNING_ANSWER2_COL = "MORNING_ANSWER2";

    public static final String NIGHT_ANSWER1_COL = "NIGHT_ANSWER1";
    public static final String NIGHT_ANSWER2_COL = "NIGHT_ANSWER2";
    public static final String NIGHT_ANSWER3_COL = "NIGHT_ANSWER3";

    public static final String DAILY_QUESTION_COL = "DAILY_QUESTION";
    public static final String DAILY_ANSWER_COL = "DAILY_ANSWER";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    DATE_COL + " DATE PRIMARY KEY," +
                    SENTENCE_COL + " TEXT," +
                    MORNING_ANSWER1_COL + " TEXT," +
                    MORNING_ANSWER2_COL + " TEXT," +
                    NIGHT_ANSWER1_COL + " TEXT," +
                    NIGHT_ANSWER2_COL + " TEXT," +
                    NIGHT_ANSWER3_COL + " TEXT," +
                    DAILY_QUESTION_COL + " TEXT," +
                    DAILY_ANSWER_COL + " TEXT" +
                    " );";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        //SQLiteDatabase db = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table " + TABLE_NAME +" (DATE INTEGER PRIMARY KEY AUTOINCREMENT,MORNING TEXT,NIGHT TEXT,TRAINING TEXT)");
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(sqLiteDatabase);
    }


    public boolean insertDailyDateAndSentence(Date date , String sentence){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        String tempDate = dateFormat.format(date);
        contentValues.put(DATE_COL,tempDate);
        contentValues.put(SENTENCE_COL,sentence);

        db.insert(TABLE_NAME,null,contentValues);
        db.close();

        return true;
    }

    public boolean insertMorningCeramony(Date date ,String answer1 , String answer2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String tempDate = dateFormat.format(date);

        contentValues.put(MORNING_ANSWER1_COL,answer1);
        contentValues.put(MORNING_ANSWER2_COL,answer2);

        db.update(TABLE_NAME,contentValues,DATE_COL+"=?",new String[]{tempDate});
        db.close();

        return true;
    }


    public boolean insertNightCeramony(Date date ,String answer1 , String answer2, String answer3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String tempDate = dateFormat.format(date);

        contentValues.put(NIGHT_ANSWER1_COL,answer1);
        contentValues.put(NIGHT_ANSWER2_COL,answer2);
        contentValues.put(NIGHT_ANSWER3_COL,answer3);

        db.update(TABLE_NAME,contentValues,DATE_COL+"=?",new String[]{tempDate});
        db.close();

        return true;
    }


    public boolean insertDailyTrainingCeramony(Date date ,String question1 , String answer1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String tempDate = dateFormat.format(date);

        contentValues.put(DAILY_QUESTION_COL,question1);
        contentValues.put(DAILY_ANSWER_COL,answer1);

        db.update(TABLE_NAME,contentValues,DATE_COL+"=?",new String[]{tempDate});
        db.close();
        return true;
    }


    public ArrayList<Day> readMonth(Date date){

        ArrayList<Day> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String tempDate = dateFormat.format(date);

        String[] projection = {DATE_COL,SENTENCE_COL,MORNING_ANSWER1_COL,MORNING_ANSWER2_COL,
                NIGHT_ANSWER1_COL,NIGHT_ANSWER2_COL,NIGHT_ANSWER3_COL,DAILY_QUESTION_COL,DAILY_ANSWER_COL};

        String first  = tempDate.substring(0,8)+"01";
        String last  = tempDate.substring(0,8)+"31";


        String selection = DATE_COL+" BETWEEN ? AND ?";
        String[] selectionArgs = new String[]{first,last};


        Cursor cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null);




        while (cursor.moveToNext()){
            Day tempDay = new Day();

            try {
                tempDay.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow(DATE_COL))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            tempDay.setDailySentence(cursor.getString(cursor.getColumnIndexOrThrow(SENTENCE_COL)));

            tempDay.setMorningAnswer1(cursor.getString(cursor.getColumnIndexOrThrow(MORNING_ANSWER1_COL)));
            tempDay.setMorningAnswer2(cursor.getString(cursor.getColumnIndexOrThrow(MORNING_ANSWER2_COL)));

            tempDay.setNightAnswer1(cursor.getString(cursor.getColumnIndexOrThrow(NIGHT_ANSWER1_COL)));
            tempDay.setNightAnswer2(cursor.getString(cursor.getColumnIndexOrThrow(NIGHT_ANSWER2_COL)));
            tempDay.setNightAnswer3(cursor.getString(cursor.getColumnIndexOrThrow(NIGHT_ANSWER3_COL)));

            tempDay.setDailyQuestion(cursor.getString(cursor.getColumnIndexOrThrow(DAILY_QUESTION_COL)));
            tempDay.setDailyAnswer(cursor.getString(cursor.getColumnIndexOrThrow(DAILY_ANSWER_COL)));


            list.add(tempDay);
        }

        cursor.close();

        return list;
    }

    public void readTesting2(Date date){

        SQLiteDatabase db = this.getReadableDatabase();
        String tempDate = dateFormat.format(date);

        String[] projection = {DATE_COL,SENTENCE_COL,MORNING_ANSWER1_COL,MORNING_ANSWER2_COL};

        String selection = DATE_COL+"= ?";
        String[] selectionArgs = {tempDate+""};

        Cursor cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,null);


        String testDate = "";
        String testSent= "";
        String morning1= "";
        String morning2= "";

        while (cursor.moveToNext()){
            testDate =cursor.getString(cursor.getColumnIndexOrThrow(DATE_COL));
            testSent =cursor.getString(cursor.getColumnIndexOrThrow(SENTENCE_COL));
            morning1 =cursor.getString(cursor.getColumnIndexOrThrow(MORNING_ANSWER1_COL));
            morning2 =cursor.getString(cursor.getColumnIndexOrThrow(MORNING_ANSWER2_COL));
        }
        cursor.close();

        Log.e("Date "+ testDate+"Sent "+testSent,"Answer1 "+morning1+"Answer2 "+morning2);

    }


}
