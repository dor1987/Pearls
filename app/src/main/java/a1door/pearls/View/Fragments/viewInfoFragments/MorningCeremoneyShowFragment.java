package a1door.pearls.View.Fragments.viewInfoFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import a1door.pearls.Logic.Interfaces.Classes.Day;
import a1door.pearls.Logic.Interfaces.Interface.onCeramonyViewListener;
import a1door.pearls.R;
import a1door.pearls.View.Activities.MainActivity;

public class MorningCeremoneyShowFragment extends android.support.v4.app.Fragment  implements onCeramonyViewListener {
    private insideCeramonyBtnsShowListener mOnCeramonyViewListener;
    private TextView answer1;
    private TextView answer2;
    private Day day;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.morning_ceremony_view, container, false);

        day = ((MainActivity)getActivity()).getDay();

        answer1 = view.findViewById(R.id.morningCeremionyViewAnswer1);
        answer2 = view.findViewById(R.id.morningCeremionyViewAnswer2);

        answer1.setText(day.getMorningAnswer1());
        answer2.setText(day.getMorningAnswer2());

        View.OnClickListener mMorningCeramonyShowBackButtonClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (mOnCeramonyViewListener != null)
                    mOnCeramonyViewListener.onCeramonyBtnsClick(0);


            }
        };

        Button morningCeramonyShowBackButton = (Button) view.findViewById(R.id.morningCeremionyViewContinueButton);
        morningCeramonyShowBackButton.setOnClickListener(mMorningCeramonyShowBackButtonClickListener);


        return view;
    }


    @Override
    public void registerinsideCeramonyBtnsShowListeners(insideCeramonyBtnsShowListener listener) {
        mOnCeramonyViewListener = listener;
    }

    @Override
    public void deleteInsideCeramonyBtnsShowListeners(insideCeramonyBtnsShowListener listener) {
        mOnCeramonyViewListener = null;
    }
/*
    public void setQuestionText(String text1,String text2){
        answer1.setText(text1);
        answer2.setText(text2);
    }
*/
}