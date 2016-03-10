package com.nikita.learnletters.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nikita.learnletters.R;
import com.nikita.learnletters.models.WordObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedWordFragment extends Fragment {

    @Bind(R.id.word_image)
    ImageView word_image;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.translation)
    TextView translation;

    @Bind(R.id.image_progress)
    ProgressBar image_progress;

    CallBack listener;
    WordObject wordObject;
    int currentIndex;
    public static SelectedWordFragment instance(WordObject wordObject, int currentIndex) {
        SelectedWordFragment selectedWordFragment = new SelectedWordFragment();
        selectedWordFragment.wordObject = wordObject;
        selectedWordFragment.currentIndex = currentIndex;
        return selectedWordFragment;
    }

    public SelectedWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_word, container, false);
        ButterKnife.bind(this,view);
        Glide.with(this).load("https:" + wordObject.getImages().get(0).getVal())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        image_progress.setVisibility(View.GONE);
                        return false;
                    }
                }).into(word_image);
        title.setText(wordObject.getTranslation());
        translation.setText(wordObject.getText());
        return view;
    }

    @OnClick(R.id.next)
    public void next() {
        if (listener != null) {
            listener.animationFinished();
            getFragmentManager().popBackStack();
        }
    }

    // Container Activity must implement this interface
    public interface CallBack {
        void animationFinished();
    }

}
