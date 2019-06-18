package a1door.pearls.Logic.Interfaces.Interface;

public interface onNewNightCeramony {

    void registerNewNightCeramonyisteners(newNightCeramony listener);
    void deleteNewNightCeramonyListeners(newNightCeramony listener);


    interface newNightCeramony{
        void updateNewNightCeramony(String text1, String text2, String text3);
    }
}
