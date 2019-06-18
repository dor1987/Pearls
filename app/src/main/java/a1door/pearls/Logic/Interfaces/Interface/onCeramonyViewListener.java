package a1door.pearls.Logic.Interfaces.Interface;

public interface onCeramonyViewListener {

    void registerinsideCeramonyBtnsShowListeners(insideCeramonyBtnsShowListener listener);
    void deleteInsideCeramonyBtnsShowListeners(insideCeramonyBtnsShowListener listener);


    interface insideCeramonyBtnsShowListener{
        void onCeramonyBtnsClick(int whereToGo);
    }
}
