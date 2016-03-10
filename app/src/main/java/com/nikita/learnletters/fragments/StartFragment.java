package com.nikita.learnletters.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nikita.learnletters.MainActivity;
import com.nikita.learnletters.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    @Bind(R.id.word_button)
    Button word_button;
    @Bind(R.id.retry_counter)
    TextView retry_counter;

    private boolean retry = false;
    private int correctCounter = 0;

    public static StartFragment instance(boolean retry,int correctCounter) {
        StartFragment startFragment = new StartFragment();
        startFragment.retry = retry;
        startFragment.correctCounter = correctCounter;
        return startFragment;
    }

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this,view);
        if (retry) {
            retry_counter.setVisibility(View.VISIBLE);
            retry_counter.setText(correctCounter + "/10");
            word_button.setText(R.string.retry);
        } else {
            retry_counter.setVisibility(View.GONE);
            word_button.setText(R.string.start_learn);
        }
        return view;
    }

    @OnClick(R.id.word_button)
    public void startClick() {
        ((MainActivity) getActivity()).setLessonProgress(0);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        TranslationFragment translationFragment = new TranslationFragment();
        fragmentTransaction.setCustomAnimations(R.anim.slide_out, R.anim.slide_in,R.anim.slide_out, R.anim.slide_in);
        fragmentTransaction.replace(R.id.fragments_holder,translationFragment);
        fragmentTransaction.commit();
    }

}
