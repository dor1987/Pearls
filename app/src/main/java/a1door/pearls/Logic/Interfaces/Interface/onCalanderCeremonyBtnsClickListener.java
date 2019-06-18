package a1door.pearls.Logic.Interfaces.Interface;

import android.app.Fragment;

public interface onCalanderCeremonyBtnsClickListener {
    void registerCeramonyBtnsShowListeners(CeramonyBtnsShowListener listener);
    void deleteCeramonyBtnsShowListeners(CeramonyBtnsShowListener listener);


    interface CeramonyBtnsShowListener{
        void onCeramonyBtnsClick(int whereToGo);
    }
}
