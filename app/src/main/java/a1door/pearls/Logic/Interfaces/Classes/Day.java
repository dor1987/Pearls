package a1door.pearls.Logic.Interfaces.Classes;

import java.util.Date;

public class Day {
    private Date date;
    private String dailySentence;
    private String morningAnswer1;
    private String morningAnswer2;
    private String nightAnswer1;
    private String nightAnswer2;
    private String nightAnswer3;
    private String dailyQuestion;
    private String dailyAnswer;

    public Day(Date date, String dailySentence, String morningAnswer1, String morningAnswer2, String nightAnswer1, String nightAnswer2, String nightAnswer3, String dailyQuestion, String dailyAnswer) {
        this.date = date;
        this.dailySentence = dailySentence;
        this.morningAnswer1 = morningAnswer1;
        this.morningAnswer2 = morningAnswer2;
        this.nightAnswer1 = nightAnswer1;
        this.nightAnswer2 = nightAnswer2;
        this.nightAnswer3 = nightAnswer3;
        this.dailyQuestion = dailyQuestion;
        this.dailyAnswer = dailyAnswer;
    }

    public Day() {
        this.date = null;
        this.dailySentence = null;
        this.morningAnswer1 = null;
        this.morningAnswer2 = null;
        this.nightAnswer1 = null;
        this.nightAnswer2 = null;
        this.nightAnswer3 = null;
        this.dailyQuestion = null;
        this.dailyAnswer = null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDailySentence() {
        return dailySentence;
    }

    public void setDailySentence(String dailySentence) {
        this.dailySentence = dailySentence;
    }

    public String getMorningAnswer1() {
        return morningAnswer1;
    }

    public void setMorningAnswer1(String morningAnswer1) {
        this.morningAnswer1 = morningAnswer1;
    }

    public String getMorningAnswer2() {
        return morningAnswer2;
    }

    public void setMorningAnswer2(String morningAnswer2) {
        this.morningAnswer2 = morningAnswer2;
    }

    public String getNightAnswer1() {
        return nightAnswer1;
    }

    public void setNightAnswer1(String nightAnswer1) {
        this.nightAnswer1 = nightAnswer1;
    }

    public String getNightAnswer2() {
        return nightAnswer2;
    }

    public void setNightAnswer2(String nightAnswer2) {
        this.nightAnswer2 = nightAnswer2;
    }

    public String getNightAnswer3() {
        return nightAnswer3;
    }

    public void setNightAnswer3(String nightAnswer3) {
        this.nightAnswer3 = nightAnswer3;
    }

    public String getDailyQuestion() {
        return dailyQuestion;
    }

    public void setDailyQuestion(String dailyQuestion) {
        this.dailyQuestion = dailyQuestion;
    }

    public String getDailyAnswer() {
        return dailyAnswer;
    }

    public void setDailyAnswer(String dailyAnswer) {
        this.dailyAnswer = dailyAnswer;
    }
}
