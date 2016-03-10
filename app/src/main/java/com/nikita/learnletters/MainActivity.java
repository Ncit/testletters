package com.nikita.learnletters;
import android.support.v4.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.ProgressBar;

import com.nikita.learnletters.fragments.StartFragment;
import com.nikita.learnletters.network.NetworkManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private String[] meaningIds = {"211138","226138","177344","196957","224324","89785"
            ,"79639","173148","136709","158582","92590","135793","68068","64441","46290","128173","51254","55112","222435"};

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        NetworkManager.downloadWords(meaningIds,width);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        StartFragment startFragment = new StartFragment();
        fragmentTransaction.add(R.id.fragments_holder,startFragment);
        fragmentTransaction.commit();
    }

    public void setLessonProgress(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
