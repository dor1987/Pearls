package a1door.pearls.View.Fragments.editInfoFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import a1door.pearls.Logic.Interfaces.Interface.onCeramonyViewListener;
import a1door.pearls.Logic.Interfaces.Interface.onNewDailyTraining;
import a1door.pearls.R;
import a1door.pearls.View.Activities.DatabaseHelper;

    public class DailyTrainingFragment extends android.support.v4.app.Fragment implements onCeramonyViewListener ,onNewDailyTraining {
    DatabaseHelper myDatabase;
    Button save;
    private onCeramonyViewListener.insideCeramonyBtnsShowListener mOnCeramonyViewListener;
    private newDailyTraining newDailyTraining;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        myDatabase = new DatabaseHelper(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.daily_training_do, container, false);

        save = (Button) view.findViewById(R.id.dailyTrainingViewContinueButton);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView question = view.findViewById(R.id.dailyTrainingDoQuestion1);
                EditText answer = view.findViewById(R.id.dailyTrainingDoQ1Input);

                //myDatabase.insertDailyTrainingCeramony(new Date(),question.getText().toString(),answer.getText().toString());

                if (newDailyTraining != null)
                    newDailyTraining.updateNewDailyTraining(question.getText().toString(),answer.getText().toString());


                if (mOnCeramonyViewListener != null)
                    mOnCeramonyViewListener.onCeramonyBtnsClick(0);

            }
        });


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


    @Override
    public void registerNewDailyTrainingisteners(newDailyTraining listener) {
        newDailyTraining = listener;
    }

    @Override
    public void deleteNewDailyTrainingListeners(newDailyTraining listener) {
        newDailyTraining = null;
    }
}
