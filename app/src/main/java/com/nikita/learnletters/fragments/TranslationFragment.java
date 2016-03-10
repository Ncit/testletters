package com.nikita.learnletters.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nikita.learnletters.MainActivity;
import com.nikita.learnletters.R;
import com.nikita.learnletters.models.WordObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslationFragment extends Fragment implements SelectedWordFragment.CallBack {

    @Bind(R.id.first_element)
    Button first_element;
    @Bind(R.id.second_element)
    Button second_element;
    @Bind(R.id.third_element)
    Button third_element;
    @Bind(R.id.fourh_element)
    Button fourh_element;
    @Bind(R.id.title)
    TextView title;

    private int min = 1;
    private int max = 1;
    private int currentIndex = 0;
    private String answerIndex = "0";
    private int correctCounter = 0;

    RealmList<WordObject> selectedForTest = new RealmList<>();

    View.OnClickListener answerSelector = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chooseAnswer((Button)v);
        }
    };
    public TranslationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translation, container, false);

        ButterKnife.bind(this,view);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<WordObject> wordObjects = realm.allObjects(WordObject.class);
        max = wordObjects.size();
        while (selectedForTest.size() < 10) {
            WordObject wordObject = wordObjects.get(generateNumber(max,min));
            if (!selectedForTest.contains(wordObject)) {
                selectedForTest.add(wordObject);
            }
        }
        setupAnswer(currentIndex);

        first_element.setOnClickListener(answerSelector);
        second_element.setOnClickListener(answerSelector);
        third_element.setOnClickListener(answerSelector);
        fourh_element.setOnClickListener(answerSelector);

        return view;
    }

    private void setupAnswer(int index) {
        WordObject wordObject = selectedForTest.get(index);
        title.setText(wordObject.getTranslation());
        List<String> randomAnswers = new ArrayList<>();
        String correctAnswerString = wordObject.getText();
        randomAnswers.add(correctAnswerString);
        for (int i = 0; i < 3; i++) {
            int number = generateNumber(wordObject.getAlternatives().size(),1);
            randomAnswers.add(wordObject.getAlternatives().get(number).getText());
        }
        long seed = System.nanoTime();
        Collections.shuffle(randomAnswers, new Random(seed));
        answerIndex = randomAnswers.indexOf(correctAnswerString) + "";
        first_element.setText(randomAnswers.get(0));
        second_element.setText(randomAnswers.get(1));
        third_element.setText(randomAnswers.get(2));
        fourh_element.setText(randomAnswers.get(3));
    }

    private int generateNumber(int max, int min) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1);
    }

    public void chooseAnswer(Button button) {
        if (currentIndex < 9) {
            ((MainActivity)getActivity()).setLessonProgress((currentIndex + 1) * 10);
            if (answerIndex.equals(button.getTag().toString())) {
                correctCounter++;
                button.setBackgroundResource(R.drawable.rounded_button_green);
                button.setTextColor(Color.WHITE);
            } else {
                button.setBackgroundResource(R.drawable.rounded_button_orange);
                button.setTextColor(Color.WHITE);
                setupButtonsCorrect();
            }
            correctAnswer();
        } else {
            finishTest();
        }
    }

    private void setupButtonsCorrect() {
        Button correctButton = (Button)getView().findViewWithTag(answerIndex);
        correctButton.setBackgroundResource(R.drawable.rounded_button_green);
        correctButton.setTextColor(Color.WHITE);

    }

    private void correctAnswer() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SelectedWordFragment selectedWordFragment = SelectedWordFragment.instance(selectedForTest.get(currentIndex),currentIndex);
        selectedWordFragment.listener = this;
        fragmentTransaction.setCustomAnimations(R.anim.slide_out, R.anim.slide_in,R.anim.slide_out, R.anim.slide_in);
        fragmentTransaction.replace(R.id.fragments_holder,selectedWordFragment).addToBackStack("selectedWordFragment");
        fragmentTransaction.commit();
    }

    private void finishTest() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        StartFragment startFragment = StartFragment.instance(true,correctCounter);
        fragmentTransaction.setCustomAnimations(R.anim.slide_out, R.anim.slide_in,R.anim.slide_out, R.anim.slide_in);
        fragmentTransaction.replace(R.id.fragments_holder,startFragment);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.close_button)
    public void skip(Button button) {
        setupButtonsCorrect();
        if (currentIndex < 9) {
            ((MainActivity) getActivity()).setLessonProgress((currentIndex + 1) * 10);
            correctAnswer();
        } else {
            finishTest();
        }
    }

    @Override
    public void animationFinished() {
        currentIndex ++;
        setupAnswer(currentIndex);
    }
}
