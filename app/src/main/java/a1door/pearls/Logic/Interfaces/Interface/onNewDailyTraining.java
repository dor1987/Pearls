package a1door.pearls.Logic.Interfaces.Interface;

public interface onNewDailyTraining {

    void registerNewDailyTrainingisteners(newDailyTraining listener);
    void deleteNewDailyTrainingListeners(newDailyTraining listener);


    interface newDailyTraining{
        void updateNewDailyTraining(String text1, String text2);
    }
}
