package a1door.pearls.View.Fragments.viewInfoFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import a1door.pearls.Logic.Interfaces.Classes.Day;
import a1door.pearls.Logic.Interfaces.Interface.getDayText;
import a1door.pearls.Logic.Interfaces.Interface.onCeramonyViewListener;
import a1door.pearls.R;
import a1door.pearls.View.Activities.MainActivity;

public class DailyTrainingShowFragment extends android.support.v4.app.Fragment implements onCeramonyViewListener {
    private onCeramonyViewListener.insideCeramonyBtnsShowListener mOnCeramonyViewListener;
    private TextView question;
    private TextView answer;
    private Day day;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_training_view, container, false);

        day = ((MainActivity)getActivity()).getDay();

        question = view.findViewById(R.id.dailyTrainingViewQuestion1);
        answer = view.findViewById(R.id.dailyTrainingViewAnswer1);

        question.setText(day.getDailyQuestion());
        answer.setText(day.getDailyAnswer());


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
    public void setQuestionText(String text){
        question.setText(text);
    }

    public void setAnswerText(String text){
    answer.setText(text);
    }
*/
}