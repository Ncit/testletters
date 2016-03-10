package com.nikita.learnletters.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nikita.learnletters.R;

/**
 * Created by nikita on 10.03.16.
 */
public class WordView extends RelativeLayout {
    private int bgColor;
    private int textColor;
    private String text;

    public WordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.WordView,
                0, 0);

        try {
            bgColor = a.getColor(R.styleable.WordView_wordBackground, Color.TRANSPARENT);
            textColor = a.getColor(R.styleable.WordView_wordTextColor, Color.BLACK);
            text = a.getString(R.styleable.WordView_wordText);
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.word_view, this);
        Button word_button = (Button) findViewById(R.id.word_button);
        CardView word_placeholder = (CardView) findViewById(R.id.word_placeholder);
        word_placeholder.setCardBackgroundColor(bgColor);
        word_button.setText(text);
        word_button.setTextColor(textColor);
    }

}