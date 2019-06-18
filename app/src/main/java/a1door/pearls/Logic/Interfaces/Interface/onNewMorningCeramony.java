package a1door.pearls.Logic.Interfaces.Interface;

public interface onNewMorningCeramony {

    void registerNewMorningCeramonyisteners(newMorningCeramony listener);
    void deleteNewMorningCeramonyListeners(newMorningCeramony listener);


    interface newMorningCeramony{
        void updateNewMorningCeramony(String text1 ,String text2);
    }
}
