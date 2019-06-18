package a1door.pearls.View.Fragments.editInfoFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import a1door.pearls.Logic.Interfaces.Interface.onCeramonyViewListener;
import a1door.pearls.Logic.Interfaces.Interface.onNewNightCeramony;
import a1door.pearls.R;
import a1door.pearls.View.Activities.DatabaseHelper;

public class NightCeremonyFragment extends android.support.v4.app.Fragment implements onCeramonyViewListener ,onNewNightCeramony {
    DatabaseHelper myDatabase;
    Button save;

    private onCeramonyViewListener.insideCeramonyBtnsShowListener mOnCeramonyViewListener;
    private  newNightCeramony newNightCeramony;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        myDatabase = new DatabaseHelper(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.night_ceremony_do, container, false);

        save = (Button) view.findViewById(R.id.nightCeremionyViewContinueButton);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText answer1 = view.findViewById(R.id.nightCeremionyDoQ1Input);
                EditText answer2 = view.findViewById(R.id.nightCeremionyDoQ2Input);
                EditText answer3 = view.findViewById(R.id.nightCeremionyDoQ3Input);

           //     myDatabase.insertNightCeramony(new Date(),answer1.getText().toString(),answer2.getText().toString(),answer3.getText().toString());

                if(newNightCeramony !=null){
                    newNightCeramony.updateNewNightCeramony(answer1.getText().toString(),answer2.getText().toString(),answer3.getText().toString());
                }
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
    public void registerNewNightCeramonyisteners(newNightCeramony listener) {
        newNightCeramony = listener;
    }

    @Override
    public void deleteNewNightCeramonyListeners(newNightCeramony listener) {
        newNightCeramony = null;
    }
}
